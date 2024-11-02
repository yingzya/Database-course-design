package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.Oorder;

/**
 * 订单管理Mapper接口
 * 
 * @author yangwang
 * @date 2024-10-25
 */
public interface OorderMapper 
{
    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    public Oorder selectOorderByOrderId(Long orderId);

    /**
     * 查询订单管理列表
     * 
     * @param oorder 订单管理
     * @return 订单管理集合
     */
    public List<Oorder> selectOorderList(Oorder oorder);

    /**
     * 新增订单管理
     * 
     * @param oorder 订单管理
     * @return 结果
     */
    public int insertOorder(Oorder oorder);

    /**
     * 修改订单管理
     * 
     * @param oorder 订单管理
     * @return 结果
     */
    public int updateOorder(Oorder oorder);

    /**
     * 删除订单管理
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    public int deleteOorderByOrderId(Long orderId);

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOorderByOrderIds(Long[] orderIds);
}
