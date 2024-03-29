package com.example.booking.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework .amqp.core.Message;
import org.springframework .amqp.core.MessageProperties ;

/**
 * @author jojo
 * @since 1.0.0
 */
@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendsecKillMessage(String message) {
        log.info("发送消息： " + message);
        rabbitTemplate .convertAndSend("seckillExchange" , "seckill.msg" , message);
    }
//
//    public void send(Object msg) {
//        log.info("发送消息： " + msg);
//        rabbitTemplate.convertAndSend("queue", msg);
//    }
//
//    public void send01(Object msg) {
//        log.info("发送消息： " + msg);
//        rabbitTemplate.convertAndSend("fanoutExchange", "", msg);
//    }
//
//    public void send03(Object msg) {
//        log.info("发送red消息： " + msg);
//        rabbitTemplate.convertAndSend("directExchange", "queue.red", msg);
//    }
//    public void send04(Object msg) {
//        log.info("发送green消息： " + msg);
//        rabbitTemplate.convertAndSend("directExchange", "queue.green", msg);
//    }
//
//    public void send05(Object msg) {
//        log.info("发送消息(被01队列接受)：" + msg);
//        rabbitTemplate.convertAndSend("topicExchange", "queue.red.message", msg);
//    }
//    public void send06(Object msg) {
//        log.info("发送消息(被两个queue接受)：" + msg);
//        rabbitTemplate.convertAndSend("topicExchange", "message.queue.green.abc", msg);
//    }
//    public void send07(String msg) {
//        log.info("发送消息(被两个queue接受)：" + msg);
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("color", "red");
//        properties.setHeader("speed", "fast");
//        Message message = new Message(msg.getBytes(), properties);
//        rabbitTemplate .convertAndSend("headersExchange" , "", message); }
//    public void send08(String msg) {
//        log.info("发送消息(被01队列接受)：" + msg);
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("color", "red");
//        properties.setHeader("speed", "normal");
//        Message message = new Message(msg.getBytes(), properties);
//        rabbitTemplate .convertAndSend("headersExchange" , "", message); }
}
