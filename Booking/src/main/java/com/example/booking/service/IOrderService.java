package com.example.booking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.booking.pojo.Order;
import com.example.booking.pojo.User;
import com.example.booking.vo.GoodsVo;
import com.example.booking.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jojo
 * @since 2023-07-07
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goods);

    OrderDetailVo detail(Long orderId);
}
