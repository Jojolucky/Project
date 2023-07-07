package com.example.booking.vo;


import com.example.booking.pojo.Goods;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/*** 商品返回对象 ** @author zhoubin * @since 1.0.0 */
@Data
@NoArgsConstructor  // 无参构造
@AllArgsConstructor // 有参构造
public class GoodsVo extends Goods {
    private BigDecimal seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}