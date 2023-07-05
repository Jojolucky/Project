package com.example.booking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.booking.pojo.User;
import com.example.booking.vo.LoginVo;
import com.example.booking.vo.RespBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jojo
 * @since 2023-06-26
 */
public interface IUserService extends IService<User> {

    RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
    // 根据cookie获取用户
//    User getByUserCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);
}
