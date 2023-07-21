package com.example.booking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.booking.mapper.GoodsMapper;
import com.example.booking.pojo.Goods;
import com.example.booking.service.IGoodsService;
import com.example.booking.vo.GoodsVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jojo
 * @since 2023-07-07
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Resource
    @Autowired
    private GoodsMapper goodsMapper;
//获取商品列表
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();

    }
//    获取商品详情

    @Override
    public GoodsVo findGoodsVoByGoodsId(Long goodsId) {

        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
