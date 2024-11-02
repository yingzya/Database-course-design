package com.ruoyi.logistics.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物流信息对象 wuliu
 * 
 * @author yangwang
 * @date 2024-10-25
 */
public class Wuliu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物流编号 */
    private String wuliuId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private Long orderId;

    /** 调度员编号 */
    @Excel(name = "调度员编号")
    private String dispatcherId;

    /** 调度员信息信息 */
    private List<Dispatcher> dispatcherList;

    public void setWuliuId(String wuliuId) 
    {
        this.wuliuId = wuliuId;
    }

    public String getWuliuId() 
    {
        return wuliuId;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setDispatcherId(String dispatcherId) 
    {
        this.dispatcherId = dispatcherId;
    }

    public String getDispatcherId() 
    {
        return dispatcherId;
    }

    public List<Dispatcher> getDispatcherList()
    {
        return dispatcherList;
    }

    public void setDispatcherList(List<Dispatcher> dispatcherList)
    {
        this.dispatcherList = dispatcherList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wuliuId", getWuliuId())
            .append("shopName", getShopName())
            .append("orderId", getOrderId())
            .append("dispatcherId", getDispatcherId())
            .append("dispatcherList", getDispatcherList())
            .toString();
    }
}
