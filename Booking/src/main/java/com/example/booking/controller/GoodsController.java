package com.example.booking.controller;

import com.example.booking.pojo.User;
import com.example.booking.service.IGoodsService;
import com.example.booking.service.IUserService;
import com.example.booking.vo.GoodsVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

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
    /**
     * 跳转商品详情页
     */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        model.addAttribute("user", user);
//        model.addAttribute("goods", goodsService .findGoodsVoByGoodsId(goodsId));
        GoodsVo goodsVo = goodsService .findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
//秒杀状态
        int secKillStatus = 0;
//剩余开始时间
        int remainSeconds = 0;
//秒杀还未开始
        if (nowDate.before(startDate)) {
            remainSeconds = (int) ((startDate.getTime()-nowDate.getTime())/1000);
// 秒杀已结束
        } else if (nowDate.after(endDate)) {
            secKillStatus = 2;
            remainSeconds = -1;
// 秒杀中
        } else {
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds" ,remainSeconds);
        model.addAttribute("secKillStatus" ,secKillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail" ;


    }
}
