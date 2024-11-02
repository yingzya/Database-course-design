package com.ruoyi.shop.service;

import java.util.List;
import com.ruoyi.shop.domain.Shop;

/**
 * 店铺管理Service接口
 * 
 * @author yangzy
 * @date 2024-10-28
 */
public interface IShopService 
{
    /**
     * 查询店铺管理
     * 
     * @param shopId 店铺管理主键
     * @return 店铺管理
     */
    public Shop selectShopByShopId(Long shopId);

    /**
     * 查询店铺管理列表
     * 
     * @param shop 店铺管理
     * @return 店铺管理集合
     */
    public List<Shop> selectShopList(Shop shop);

    /**
     * 新增店铺管理
     * 
     * @param shop 店铺管理
     * @return 结果
     */
    public int insertShop(Shop shop);

    /**
     * 修改店铺管理
     * 
     * @param shop 店铺管理
     * @return 结果
     */
    public int updateShop(Shop shop);

    /**
     * 批量删除店铺管理
     * 
     * @param shopIds 需要删除的店铺管理主键集合
     * @return 结果
     */
    public int deleteShopByShopIds(Long[] shopIds);

    /**
     * 删除店铺管理信息
     * 
     * @param shopId 店铺管理主键
     * @return 结果
     */
    public int deleteShopByShopId(Long shopId);
}
