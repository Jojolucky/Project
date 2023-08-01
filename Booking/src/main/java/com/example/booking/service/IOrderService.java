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
// 秒杀
    Order seckill(User user, GoodsVo goods);
// 订单详情
    OrderDetailVo detail(Long orderId);
// 获取秒杀地址
    String createPath(User user, Long goodsId);
// 校验秒杀地址
    boolean checkPath(User user, Long goodsId, String path);
}
