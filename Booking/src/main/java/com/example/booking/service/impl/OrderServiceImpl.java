package com.example.booking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.booking.exception.GlobalException;
import com.example.booking.mapper.OrderMapper;
import com.example.booking.pojo.Order;
import com.example.booking.pojo.SeckillGoods;
import com.example.booking.pojo.SeckillOrder;
import com.example.booking.pojo.User;
import com.example.booking.service.IGoodsService;
import com.example.booking.service.IOrderService;
import com.example.booking.service.ISeckillGoodsService;
import com.example.booking.service.ISeckillOrderService;
import com.example.booking.utils.MD5Util;
import com.example.booking.utils.UUIDUtil;
import com.example.booking.vo.GoodsVo;
import com.example.booking.vo.OrderDetailVo;
import com.example.booking.vo.RespBeanEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jojo
 * @since 2023-07-07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    @Resource
    private OrderMapper orderMapper;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private RedisTemplate redisTemplate ;


    @Override
    @Transactional
    public Order seckill(User user, GoodsVo goods) {
        ValueOperations valueOperations = redisTemplate .opsForValue();
//秒杀商品表减库存
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
//        seckillGoodsService.updateById(seckillGoods);// 没有任何判断，就会导致库存超卖
        boolean seckillGoodsResult = seckillGoodsService.update(new UpdateWrapper<SeckillGoods>().setSql("stock_count = " + "stock_count - 1").eq(
                "goods_id", goods.getId()).gt("stock_count", 0));
//        if (!seckillGoodsResult) {
//            return null;
//        }
        if (seckillGoods .getStockCount() < 1) {
//判断是否还有库存
            valueOperations .set("isStockEmpty:" + goods.getId(), "0");
            return null;
        }

//生成订单
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setGoodsId(goods.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);
//生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setUserId(user.getUserId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrderService.save(seckillOrder);
        // 解决库存超卖，写入redis
        redisTemplate .opsForValue().set("order:" + user.getUserId() + ":" + goods.getId(), seckillOrder);
        return order;
    }

    @Override
    public OrderDetailVo detail(Long orderId) {
        if (null == orderId) {
            throw new GlobalException(RespBeanEnum.ORDER_NOT_EXIST);
        }
        Order order = orderMapper.selectById(orderId);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(order.getGoodsId());
        OrderDetailVo detail = new OrderDetailVo();
        detail.setGoodsVo(goodsVo);
        detail.setOrder(order);
        return detail;
    }
//    获取秒杀地址

    @Override
    public String createPath(User user, Long goodsId) {
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisTemplate .opsForValue().set("seckillPath:" + user.getUserId() + ":" + goodsId, str, 60, TimeUnit.SECONDS);
        return str;
    }
// 校验秒杀地址
    @Override
    public boolean checkPath(User user, Long goodsId, String path) {
        if (user==null || StringUtils.isEmpty(path)){
            return false;
        }
        String redisPath = (String) redisTemplate .opsForValue().get("seckillPath:" + user.getUserId() + ":" + goodsId);
        return path.equals(redisPath);
    }
}
