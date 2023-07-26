package com.example.booking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.booking.pojo.Order;
import com.example.booking.pojo.SeckillMessage;
import com.example.booking.pojo.SeckillOrder;
import com.example.booking.pojo.User;
import com.example.booking.rabbitmq.MQSender;
import com.example.booking.service.IGoodsService;
import com.example.booking.service.IOrderService;
import com.example.booking.service.ISeckillOrderService;
import com.example.booking.utils.JsonUtil;
import com.example.booking.vo.GoodsVo;
import com.example.booking.vo.RespBean;
import com.example.booking.vo.RespBeanEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MQSender mqSender;

    private Map<Long, Boolean> EmptyStockMap = new HashMap<>();  // map用于内存标记，减少redis的库存访问

    @RequestMapping("/doSeckill2")
    public String doSeckill2(Model model, User user, Long goodsId) {
        if (user == null) {
            return "login";
        }
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
//判断库存
        if (goods.getStockCount() < 1) {
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "seckillFail";
        }
//判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new
                QueryWrapper<SeckillOrder>().eq("user_id", user.getUserId()).eq("goods_id", goodsId));
        if (seckillOrder != null) {
            model.addAttribute("errmsg", RespBeanEnum.REPEATE_ERROR.getMessage());
            return "seckillFail";
        }
        Order order = orderService.seckill(user, goods);
        model.addAttribute("order", order);
        model.addAttribute("goods", goods);
        return "orderDetail";
    }


    //    以下秒杀静态化处理
    @RequestMapping(value = "/doSeckill3", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill3(Model model, User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
//判断库存
        if (goods.getStockCount() < 1) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
//判断是否重复抢购
//        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
//                .eq("user_id", user.getUserId()).eq("goods_id", goodsId));
//        if (seckillOrder != null) {
//            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
//        }
//        Order order = orderService.seckill(user, goods);
//        return RespBean.success(order);

        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getUserId() + ":" + goodsId);
        if (seckillOrder != null) {
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
        Order order = orderService.seckill(user, goods);
        return RespBean.success(order);

    }


    //    以下redis接口优化，预减库存
    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSeckill(Model model, User user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        // 以下为redis的操作
        ValueOperations valueOperations = redisTemplate.opsForValue();
//判断是否重复抢购
        String seckillOrderJson = (String) valueOperations.get("order:" + user.getUserId() + ":" + goodsId);
        if (!StringUtils.isEmpty(seckillOrderJson)) {
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
//        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getUserId() + ":" + goodsId);
//        if(seckillOrder != null){
//            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
//        }
//内存标记 ,减少Redis访问
        if (EmptyStockMap.get(goodsId)) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
//预减库存
        Long stock = valueOperations.decrement("seckillGoods:" + goodsId);
        if (stock < 0) {
//            EmptyStockMap.put(goodsId, true);
            valueOperations.increment("seckillGoods:" + goodsId);
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
// 请求入队，立即返回排队中
        SeckillMessage message = new SeckillMessage(user, goodsId);
        mqSender.sendsecKillMessage(JsonUtil.object2JsonStr(message));
        return RespBean.success(0);  // 0 表示正在排队中
    }


    /**
     * 系统初始化，把商品库存数量加载到Redis *
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> list = goodsService.findGoodsVo();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(goodsVo -> {
            redisTemplate.opsForValue().set("seckillGoods:" + goodsVo.getId(), goodsVo.getStockCount());
            EmptyStockMap.put(goodsVo.getId(), false);
        });
    }
}
