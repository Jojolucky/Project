package com.example.booking.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回状态枚举 *
 * @author zhoubin
 * @since 1.0.0
 */
@ToString
@Getter
@AllArgsConstructor
public enum RespBeanEnum {
    //通用状态码
    SUCCESS(200,"success"),
    ERROR(500,"服务端异常"),
    //登录模块5002xx
//    SESSION_ERROR(500210,"session不存在或者已经失效"),
    LOGIN_ERROR(500210,"用户名或者密码错误"),
    MOBILE_ERROR(500211,"手机号码格式错误"),
    BIND_ERROR(500212,"参数校验异常" ),
    EMPTY_STOCK(500212,"参数校验异常" ),
    MOBILE_NOT_EXIST(500213, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500214, "密码更新失败"),

    // 秒杀模块5005xx
    REPEATE_ERROR(500501,"商品每人限购一件");


    private final Integer code;
    private final String message;

}