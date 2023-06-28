package com.example.booking.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.booking.mapper.UserMapper;
import com.example.booking.pojo.User;
import com.example.booking.service.IUserService;
import com.example.booking.utils.MD5Util;
import com.example.booking.utils.ValidatorUtil;
import com.example.booking.vo.LoginVo;
import com.example.booking.vo.RespBean;
import com.example.booking.vo.RespBeanEnum;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public RespBean login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        // 判断手机号，密码是否为空
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // 判断是否为有效的手机号码
        if (!ValidatorUtil.isMobile(mobile)) {
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }
        //根据手机号获取用户
        User user = userMapper.selectById(mobile);
//        System.out.println(user.getUserId());
        if (null == user) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        //校验密码
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassWord())) {
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        return RespBean.success();
    }
}
