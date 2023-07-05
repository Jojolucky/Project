package com.example.booking.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.booking.exception.GlobalException;
import com.example.booking.mapper.UserMapper;
import com.example.booking.pojo.User;
import com.example.booking.service.IUserService;
import com.example.booking.utils.CookieUtil;
import com.example.booking.utils.MD5Util;
import com.example.booking.utils.UUIDUtil;
import com.example.booking.vo.LoginVo;
import com.example.booking.vo.RespBean;
import com.example.booking.vo.RespBeanEnum;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jojo
 * @since 2023-06-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        // 过多的参数校验， 简化代码？？？ --》 使用validation的组件, jsr303
//        // 判断手机号，密码是否为空
//        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        // 判断是否为有效的手机号码
//        if (!ValidatorUtil.isMobile(mobile)) {
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if (null == user) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        //校验密码
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassWord())) {
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        //生成cookie
        String ticket = UUIDUtil.uuid();
        // 写入redis
        redisTemplate.opsForValue().set("user:" + ticket, user);
        request.getSession().setAttribute(ticket, user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

//    @Override
//    public User getByUserCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
//        if (StringUtils.isEmpty(userTicket)) {
//            return null;
//        }
//        User user = (User)redisTemplate.opsForValue().get("user:" + userTicket);
//        if(user != null){
//            CookieUtil.setCookie(request, response, "userTicker", userTicket);
//        }
//        return user;
//
//    }
}
