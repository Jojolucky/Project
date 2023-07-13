package com.example.booking.controller;


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

//    用户信息（测试）
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }

}

