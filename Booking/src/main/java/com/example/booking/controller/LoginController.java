package com.example.booking.controller;

import com.example.booking.service.IUserService;
import com.example.booking.vo.LoginVo;
import com.example.booking.vo.RespBean;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 登录 *
 * @author Jojo
 * @since 1.0.0
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 跳转登录页 *
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    /**
     * 登录
     * @return */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
//        log.info(loginVo.toString());
        return userService.login(loginVo, request, response);
    }
}

