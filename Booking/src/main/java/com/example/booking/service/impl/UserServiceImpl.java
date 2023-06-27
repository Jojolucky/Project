package com.example.booking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.booking.mapper.UserMapper;
import com.example.booking.pojo.User;
import com.example.booking.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jojo
 * @since 2023-06-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
