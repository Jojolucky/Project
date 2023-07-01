package com.example.booking.controller;


import com.example.booking.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品 ** @author zhoubin * @since 1.0.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    /*** 跳转登录页 ** @return */
    @RequestMapping("/toList")
    public String toLogin(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if (null == user) {
            return "login";
        }
        model.addAttribute("user", user);
        return "goodsList";
    }
}