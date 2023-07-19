package com.example.booking.vo;

import com.example.booking.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhoubin
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {

    private User user;
    private GoodsVo goodsVo;
    private int secKillStatus;
    private int remainSeconds;

}
