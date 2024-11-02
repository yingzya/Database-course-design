package com.ruoyi.order.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.Oorder;
import com.ruoyi.order.service.IOorderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单管理Controller
 * 
 * @author yangwang
 * @date 2024-10-25
 */
@RestController
@RequestMapping("/order/oorder")
public class OorderController extends BaseController
{
    @Autowired
    private IOorderService oorderService;

    /**
     * 查询订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('order:oorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(Oorder oorder)
    {
        startPage();
        List<Oorder> list = oorderService.selectOorderList(oorder);
        return getDataTable(list);
    }

    /**
     * 导出订单管理列表
     */
    @PreAuthorize("@ss.hasPermi('order:oorder:export')")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Oorder oorder)
    {
        List<Oorder> list = oorderService.selectOorderList(oorder);
        ExcelUtil<Oorder> util = new ExcelUtil<Oorder>(Oorder.class);
        util.exportExcel(response, list, "订单管理数据");
    }

    /**
     * 获取订单管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:oorder:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(oorderService.selectOorderByOrderId(orderId));
    }

    /**
     * 新增订单管理
     */
    @PreAuthorize("@ss.hasPermi('order:oorder:add')")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Oorder oorder)
    {
        return toAjax(oorderService.insertOorder(oorder));
    }

    /**
     * 修改订单管理
     */
    @PreAuthorize("@ss.hasPermi('order:oorder:edit')")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Oorder oorder)
    {
        return toAjax(oorderService.updateOorder(oorder));
    }

    /**
     * 删除订单管理
     */
    @PreAuthorize("@ss.hasPermi('order:oorder:remove')")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(oorderService.deleteOorderByOrderIds(orderIds));
    }
}
