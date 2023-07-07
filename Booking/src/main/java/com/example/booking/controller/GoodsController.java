package com.example.booking.controller;

import com.example.booking.pojo.User;
import com.example.booking.service.IGoodsService;
import com.example.booking.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品 ** @author zhoubin
 * * @since 1.0.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;

    /**
     * 跳转登录页 *
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toList(Model model,User user){
//    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String ticket) {
//        if (StringUtils.isEmpty(ticket)) {
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getByUserCookie(ticket, request, response);
//        if (null == user) {
//            return "login";
//        }
        // put user into the page
        model.addAttribute("user", user);
        // put goods into the page
        model.addAttribute("goodsList", goodsService.findGoodsVo());

        return "goodsList";
    }
}
