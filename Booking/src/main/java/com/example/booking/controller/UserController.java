package com.example.booking.controller;


import com.example.booking.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.booking.pojo.User;
import com.example.booking.vo.RespBean;
import org.springframework .web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jojo
 * @since 2023-06-26
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MQSender mqSender;

//    用户信息（测试）
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq")
//    @ResponseBody
//    public void mq() {
//        mqSender.send("Hello");
//    }
//    /**
//     * 测试Fanout发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public void mq01() {
//        mqSender.send01("Hello");
//    }
//    /**
//     * 测试Direct发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/direct01")
//    @ResponseBody
//    public void mq03() {
//        mqSender.send03("Hello,Red");
//    }
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/direct02")
//    @ResponseBody
//    public void mq04() {
//        mqSender.send04("Hello,Green");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/topic01")
//    @ResponseBody
//    public void mq05() {
//        mqSender.send05("Hello,Red");
//    }
//    @RequestMapping("/mq/topic02")
//    @ResponseBody
//    public void mq06() {
//        mqSender.send06("Hello,Green");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/header01")
//    @ResponseBody
//    public void mq07() {
//        mqSender.send07("Hello,header01");
//    }
//
//    /**
//     * 测试发送RabbitMQ消息
//     */
//    @RequestMapping("/mq/header02")
//    @ResponseBody
//    public void mq08() {
//        mqSender.send08("Hello,header02");
//    }

}

