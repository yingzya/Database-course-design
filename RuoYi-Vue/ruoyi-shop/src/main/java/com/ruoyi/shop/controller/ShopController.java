package com.ruoyi.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.shop.domain.Shop;
import com.ruoyi.shop.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 店铺管理Controller
 *
 * @author yangzy
 * @date 2024-10-28
 */
@RestController
@RequestMapping("/shop/dianpu")
public class ShopController extends BaseController
{
    @Autowired
    private IShopService shopService;

    /**
     * 查询店铺管理列表
     */
    @PreAuthorize("@ss.hasPermi('shop:dianpu:list')")
    @GetMapping("/list")
    public TableDataInfo list(Shop shop)
    {
        startPage();
        List<Shop> list = shopService.selectShopList(shop);
        return getDataTable(list);
    }

    /**
     * 导出店铺管理列表
     */
    @PreAuthorize("@ss.hasPermi('shop:dianpu:export')")
    @Log(title = "店铺管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Shop shop)
    {
        List<Shop> list = shopService.selectShopList(shop);
        ExcelUtil<Shop> util = new ExcelUtil<Shop>(Shop.class);
        util.exportExcel(response, list, "店铺管理数据");
    }

    /**
     * 获取店铺管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:dianpu:query')")
    @GetMapping(value = "/{shopId}")
    public AjaxResult getInfo(@PathVariable("shopId") Long shopId)
    {
        return success(shopService.selectShopByShopId(shopId));
    }

    /**
     * 新增店铺管理
     */
    @PreAuthorize("@ss.hasPermi('shop:dianpu:add')")
    @Log(title = "店铺管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Shop shop)
    {
        return toAjax(shopService.insertShop(shop));
    }

    /**
     * 修改店铺管理
     */
    @PreAuthorize("@ss.hasPermi('shop:dianpu:edit')")
    @Log(title = "店铺管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Shop shop)
    {
        return toAjax(shopService.updateShop(shop));
    }

    /**
     * 删除店铺管理
     */
    @PreAuthorize("@ss.hasPermi('shop:dianpu:remove')")
    @Log(title = "店铺管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shopIds}")
    public AjaxResult remove(@PathVariable Long[] shopIds)
    {
        return toAjax(shopService.deleteShopByShopIds(shopIds));
    }
}
