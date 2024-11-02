package com.ruoyi.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单管理对象 oorder
 * 
 * @author yangwang
 * @date 2024-10-25
 */
public class Oorder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单编号 */
    private Long orderId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private Long orderMoney;

    /** 订餐方式 */
    @Excel(name = "订餐方式")
    private Long orderWay;

    /** 顾客电话 */
    @Excel(name = "顾客电话")
    private String consPhone;

    /** 顾客姓名 */
    @Excel(name = "顾客姓名")
    private String consName;

    /** 顾客地址 */
    @Excel(name = "顾客地址")
    private String consAddre;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long checked;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setOrderMoney(Long orderMoney) 
    {
        this.orderMoney = orderMoney;
    }

    public Long getOrderMoney() 
    {
        return orderMoney;
    }
    public void setOrderWay(Long orderWay) 
    {
        this.orderWay = orderWay;
    }

    public Long getOrderWay() 
    {
        return orderWay;
    }
    public void setConsPhone(String consPhone) 
    {
        this.consPhone = consPhone;
    }

    public String getConsPhone() 
    {
        return consPhone;
    }
    public void setConsName(String consName) 
    {
        this.consName = consName;
    }

    public String getConsName() 
    {
        return consName;
    }
    public void setConsAddre(String consAddre) 
    {
        this.consAddre = consAddre;
    }

    public String getConsAddre() 
    {
        return consAddre;
    }
    public void setChecked(Long checked) 
    {
        this.checked = checked;
    }

    public Long getChecked() 
    {
        return checked;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("shopName", getShopName())
            .append("orderMoney", getOrderMoney())
            .append("orderWay", getOrderWay())
            .append("consPhone", getConsPhone())
            .append("consName", getConsName())
            .append("consAddre", getConsAddre())
            .append("checked", getChecked())
            .append("createTime", getCreateTime())
            .toString();
    }
}
