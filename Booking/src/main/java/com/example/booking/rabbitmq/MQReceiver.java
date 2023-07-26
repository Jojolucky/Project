package com.example.booking.rabbitmq;

import com.example.booking.pojo.SeckillMessage;
import com.example.booking.pojo.User;
import com.example.booking.service.IGoodsService;
import com.example.booking.service.IOrderService;
import com.example.booking.utils.JsonUtil;
import com.example.booking.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import org.springframework.util.StringUtils;

/**
 * @author jojo
 * @since 1.0.0
 */
@Service
@Slf4j
public class MQReceiver {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IOrderService orderService;
    @RabbitListener(queues = "seckillQueue")
    public void receive(String msg) {
        log.info("QUEUE接受消息： " + msg);
        SeckillMessage message = JsonUtil.jsonStr2Object(msg,
                SeckillMessage.class);
        Long goodsId = message.getGoodsId();
        User user = message.getUser();
        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
//判断库存
        if (goods.getStockCount() < 1) {
            return;
        }
//判断是否重复抢购
// SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id",
//        user.getId()).eq(
//        "goods_id",
//        goodsId));
        String seckillOrderJson = (String)
                redisTemplate.opsForValue().get("order:" + user.getUserId() + ":" + goodsId);
        if (!StringUtils.isEmpty(seckillOrderJson)) {
            return;
        }
        orderService.seckill(user, goods);
    }
}

// 以下为测试rabbitmq时候的code

//    @RabbitListener(queues = "queue")
//    public void receive(Object msg) {
//        log.info("接受消息： " + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout01")
//    public void receive01(Object msg) {
//        log.info("QUEUE01接受消息： " + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout02")
//    public void receive02(Object msg) {
//        log.info("QUEUE02接受消息： " + msg);
//    }
//
//    @RabbitListener(queues = "queue_direct01")
//    public void receive03(Object msg) {
//        log.info("QUEUE01接受消息： " + msg);
//    }
//
//    @RabbitListener(queues = "queue_direct02")
//    public void receive04(Object msg) {
//        log.info("QUEUE02接受消息： " + msg);
//    }
//
//    @RabbitListener(queues = "queue_topic01")
//    public void receive05(Object msg) {
//        log.info("QUEUE01接受消息： " + msg);
//    }
//
//    @RabbitListener(queues = "queue_topic02")
//    public void receive06(Object msg) {
//        log.info("QUEUE02接受消息： " + msg);
//    }
//
//    @RabbitListener(queues = "queue_header01")
//    public void receive07(Message message) {
//        log.info("QUEUE01接受Message对象： " + message);
//        log.info("QUEUE01接受消息： " + new String(message.getBody()));
//    }
//
//    @RabbitListener(queues = "queue_header02")
//    public void receive08(Message message) {
//        log.info("QUEUE02接受Message对象： " + message);
//        log.info("QUEUE02接受消息： " + new String(message.getBody()));
//    }
//}
