package com.example.booking.controller;

import com.example.booking.pojo.User;
import com.example.booking.service.IGoodsService;
import com.example.booking.service.IUserService;
import com.example.booking.vo.GoodsVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


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
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ResourceLoader resourceLoader;


    /**
     * 跳转登录页 *
     * @return
     */
    @RequestMapping("/toList")
//    @RequestMapping(value = "/toList", produces = "text/html;charset=utf-8")
//    @ResponseBody
    public String toList(Model model, User user ) {

//        ValueOperations valueOperations = redisTemplate.opsForValue();
//// Redis中获取页面，如果不为空，直接返回页面
//        String html = (String) valueOperations.get("goodsList");
//        if (!StringUtils.isEmpty(html)) {
//            return html;
//        }
// put user into the page
        model.addAttribute("user", user);
        // put goods into the page
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";

//        Context context = new Context();
//        context.setVariable("response", response);
//        context.setVariable("request", request);
//        context.setLocale(request.getLocale());
//        context.setVariable("servletContext", request.getServletContext());
//        context.setVariables(model.asMap());
//

        //如果为空，手动渲染，存入Redis并返回
//        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
//        html = thymeleafViewResolver.getTemplateEngine().process("templates/goodsList.html", context);
//
//        if (!StringUtils.isEmpty(html)) {
//            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
//        }
//        return html;
    }

    /**
     * 跳转商品详情页
     */
    @RequestMapping("/toDetail/{goodsId}")
//    @RequestMapping(value = "/toDetail/{goodsId}" , produces = "text/html;charset=utf-8")
//    @ResponseBody
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {

//        ValueOperations valueOperations = redisTemplate .opsForValue();
////Redis中获取页面，如果不为空，直接返回页面
//        String html = (String) valueOperations .get("goodsDetail:" + goodsId); if (!StringUtils .isEmpty(html)) {
//            return html;
//        }

        model.addAttribute("user", user);
//        model.addAttribute("goods", goodsService .findGoodsVoByGoodsId(goodsId));
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
//秒杀状态
        int secKillStatus = 0;
//剩余开始时间
        int remainSeconds = 0;
//秒杀还未开始
        if (nowDate.before(startDate)) {
            remainSeconds = (int) ((startDate.getTime() - nowDate.getTime()) / 1000);
// 秒杀已结束
        } else if (nowDate.after(endDate)) {
            secKillStatus = 2;
            remainSeconds = -1;
// 秒杀中
        } else {
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("secKillStatus", secKillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";

////如果为空，手动渲染，存入Redis并返回
//        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
//        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail", context);
//        if (!StringUtils.isEmpty(html)) {
//            valueOperations.set("goodsDetail:" + goodsId, html, 60, TimeUnit.SECONDS);
//        }
//        return html;
    }
}

