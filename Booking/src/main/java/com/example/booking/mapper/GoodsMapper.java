package com.example.booking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.booking.pojo.Goods;
import com.example.booking.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jojo
 * @since 2023-07-07
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    //获取商品列表

    List<GoodsVo> findGoodsVo();
    //获取商品详情
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
