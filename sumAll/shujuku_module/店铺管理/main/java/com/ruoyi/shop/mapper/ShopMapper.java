package com.ruoyi.shop.mapper;

import java.util.List;
import com.ruoyi.shop.domain.Shop;
import com.ruoyi.shop.domain.Dish;

/**
 * 店铺管理Mapper接口
 * 
 * @author yangzy
 * @date 2024-10-28
 */
public interface ShopMapper 
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
     * 删除店铺管理
     * 
     * @param shopId 店铺管理主键
     * @return 结果
     */
    public int deleteShopByShopId(Long shopId);

    /**
     * 批量删除店铺管理
     * 
     * @param shopIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopByShopIds(Long[] shopIds);

    /**
     * 批量删除菜品管理
     * 
     * @param shopIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDishByNames(Long[] shopIds);
    
    /**
     * 批量新增菜品管理
     * 
     * @param dishList 菜品管理列表
     * @return 结果
     */
    public int batchDish(List<Dish> dishList);
    

    /**
     * 通过店铺管理主键删除菜品管理信息
     * 
     * @param shopId 店铺管理ID
     * @return 结果
     */
    public int deleteDishByName(Long shopId);
}
