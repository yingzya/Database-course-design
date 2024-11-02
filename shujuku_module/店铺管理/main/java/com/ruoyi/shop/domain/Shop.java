package com.ruoyi.shop.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺管理对象 tb_shop
 * 
 * @author yangzy
 * @date 2024-10-28
 */
public class Shop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店铺ID */
    private Long shopId;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺地址 */
    @Excel(name = "店铺地址")
    private String shopAddress;

    /** 店铺电话 */
    @Excel(name = "店铺电话")
    private String shopPhone;

    /** 营业状态 */
    @Excel(name = "营业状态")
    private Long shopStatus;

    /** 菜品管理信息 */
    private List<Dish> dishList;

    public void setShopId(Long shopId) 
    {
        this.shopId = shopId;
    }

    public Long getShopId() 
    {
        return shopId;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setShopAddress(String shopAddress) 
    {
        this.shopAddress = shopAddress;
    }

    public String getShopAddress() 
    {
        return shopAddress;
    }
    public void setShopPhone(String shopPhone) 
    {
        this.shopPhone = shopPhone;
    }

    public String getShopPhone() 
    {
        return shopPhone;
    }
    public void setShopStatus(Long shopStatus) 
    {
        this.shopStatus = shopStatus;
    }

    public Long getShopStatus() 
    {
        return shopStatus;
    }

    public List<Dish> getDishList()
    {
        return dishList;
    }

    public void setDishList(List<Dish> dishList)
    {
        this.dishList = dishList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("shopId", getShopId())
            .append("shopName", getShopName())
            .append("shopAddress", getShopAddress())
            .append("shopPhone", getShopPhone())
            .append("shopStatus", getShopStatus())
            .append("dishList", getDishList())
            .toString();
    }
}
