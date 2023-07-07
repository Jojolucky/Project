package com.example.booking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.booking.pojo.Goods;
import com.example.booking.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jojo
 * @since 2023-07-07
 */
public interface IGoodsService extends IService<Goods> {
//获取商品列表
    List<GoodsVo> findGoodsVo();
}
