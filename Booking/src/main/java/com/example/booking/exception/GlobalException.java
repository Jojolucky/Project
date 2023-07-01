package com.example.booking.exception;


import com.example.booking.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*** 全局异常 *
 GlobalExceptionHandler
 * @author zhoubin
 * @since 1.0.0
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private RespBeanEnum respBeanEnum;
}