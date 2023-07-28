package com.example.booking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.booking.pojo.SeckillOrder;
import com.example.booking.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jojo
 * @since 2023-07-07
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getResult(User user, Long goodsId);
}
