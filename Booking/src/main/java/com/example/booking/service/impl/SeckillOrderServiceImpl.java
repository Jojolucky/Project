package com.example.booking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.booking.mapper.SeckillOrderMapper;
import com.example.booking.pojo.SeckillOrder;
import com.example.booking.pojo.User;
import com.example.booking.service.ISeckillOrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jojo
 * @since 2023-07-07
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {

    @Autowired
    @Resource
    private SeckillOrderMapper seckillOrderMapper ;
    @Autowired
    private RedisTemplate redisTemplate ;

    /**
     * 获取秒杀结果 *
     * @param user
     * @param goodsId
     * @return
     */
    @Override
    public Long getResult(User user, Long goodsId) {
        SeckillOrder seckillOrder = seckillOrderMapper .selectOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getUserId()).eq("goods_id", goodsId));
        if (null != seckillOrder) {
            return seckillOrder .getId();
        } else {
            if (redisTemplate .hasKey("isStockEmpty:" + goodsId)) {
                return -1L;
            }else {
                return 0L;
            }
        }
    }
}
