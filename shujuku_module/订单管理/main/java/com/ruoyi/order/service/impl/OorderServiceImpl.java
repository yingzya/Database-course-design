package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OorderMapper;
import com.ruoyi.order.domain.Oorder;
import com.ruoyi.order.service.IOorderService;

/**
 * 订单管理Service业务层处理
 * 
 * @author yangwang
 * @date 2024-10-25
 */
@Service
public class OorderServiceImpl implements IOorderService 
{
    @Autowired
    private OorderMapper oorderMapper;

    /**
     * 查询订单管理
     * 
     * @param orderId 订单管理主键
     * @return 订单管理
     */
    @Override
    public Oorder selectOorderByOrderId(Long orderId)
    {
        return oorderMapper.selectOorderByOrderId(orderId);
    }

    /**
     * 查询订单管理列表
     * 
     * @param oorder 订单管理
     * @return 订单管理
     */
    @Override
    public List<Oorder> selectOorderList(Oorder oorder)
    {
        return oorderMapper.selectOorderList(oorder);
    }

    /**
     * 新增订单管理
     * 
     * @param oorder 订单管理
     * @return 结果
     */
    @Override
    public int insertOorder(Oorder oorder)
    {
        oorder.setCreateTime(DateUtils.getNowDate());
        return oorderMapper.insertOorder(oorder);
    }

    /**
     * 修改订单管理
     * 
     * @param oorder 订单管理
     * @return 结果
     */
    @Override
    public int updateOorder(Oorder oorder)
    {
        return oorderMapper.updateOorder(oorder);
    }

    /**
     * 批量删除订单管理
     * 
     * @param orderIds 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOorderByOrderIds(Long[] orderIds)
    {
        return oorderMapper.deleteOorderByOrderIds(orderIds);
    }

    /**
     * 删除订单管理信息
     * 
     * @param orderId 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOorderByOrderId(Long orderId)
    {
        return oorderMapper.deleteOorderByOrderId(orderId);
    }
}
