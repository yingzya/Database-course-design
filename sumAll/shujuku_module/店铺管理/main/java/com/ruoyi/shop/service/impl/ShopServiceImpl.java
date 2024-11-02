package com.ruoyi.shop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.shop.domain.Dish;
import com.ruoyi.shop.mapper.ShopMapper;
import com.ruoyi.shop.domain.Shop;
import com.ruoyi.shop.service.IShopService;

/**
 * 店铺管理Service业务层处理
 * 
 * @author yangzy
 * @date 2024-10-28
 */
@Service
public class ShopServiceImpl implements IShopService 
{
    @Autowired
    private ShopMapper shopMapper;

    /**
     * 查询店铺管理
     * 
     * @param shopId 店铺管理主键
     * @return 店铺管理
     */
    @Override
    public Shop selectShopByShopId(Long shopId)
    {
        return shopMapper.selectShopByShopId(shopId);
    }

    /**
     * 查询店铺管理列表
     * 
     * @param shop 店铺管理
     * @return 店铺管理
     */
    @Override
    public List<Shop> selectShopList(Shop shop)
    {
        return shopMapper.selectShopList(shop);
    }

    /**
     * 新增店铺管理
     * 
     * @param shop 店铺管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertShop(Shop shop)
    {
        int rows = shopMapper.insertShop(shop);
        insertDish(shop);
        return rows;
    }

    /**
     * 修改店铺管理
     * 
     * @param shop 店铺管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateShop(Shop shop)
    {
        shopMapper.deleteDishByName(shop.getShopId());
        insertDish(shop);
        return shopMapper.updateShop(shop);
    }

    /**
     * 批量删除店铺管理
     * 
     * @param shopIds 需要删除的店铺管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteShopByShopIds(Long[] shopIds)
    {
        shopMapper.deleteDishByNames(shopIds);
        return shopMapper.deleteShopByShopIds(shopIds);
    }

    /**
     * 删除店铺管理信息
     * 
     * @param shopId 店铺管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteShopByShopId(Long shopId)
    {
        shopMapper.deleteDishByName(shopId);
        return shopMapper.deleteShopByShopId(shopId);
    }

    /**
     * 新增菜品管理信息
     * 
     * @param shop 店铺管理对象
     */
    public void insertDish(Shop shop)
    {
        List<Dish> dishList = shop.getDishList();
        Long shopId = shop.getShopId();
        if (StringUtils.isNotNull(dishList))
        {
            List<Dish> list = new ArrayList<Dish>();
            for (Dish dish : dishList)
            {
                dish.setName(shopId);
                list.add(dish);
            }
            if (list.size() > 0)
            {
                shopMapper.batchDish(list);
            }
        }
    }
}
