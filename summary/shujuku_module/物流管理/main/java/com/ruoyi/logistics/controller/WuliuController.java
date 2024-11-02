package com.ruoyi.logistics.controller;

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
import com.ruoyi.logistics.domain.Wuliu;
import com.ruoyi.logistics.service.IWuliuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物流信息Controller
 * 
 * @author yangwang
 * @date 2024-10-25
 */
@RestController
@RequestMapping("/logistics/wuliu")
public class WuliuController extends BaseController
{
    @Autowired
    private IWuliuService wuliuService;

    /**
     * 查询物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('logistics:wuliu:list')")
    @GetMapping("/list")
    public TableDataInfo list(Wuliu wuliu)
    {
        startPage();
        List<Wuliu> list = wuliuService.selectWuliuList(wuliu);
        return getDataTable(list);
    }

    /**
     * 导出物流信息列表
     */
    @PreAuthorize("@ss.hasPermi('logistics:wuliu:export')")
    @Log(title = "物流信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Wuliu wuliu)
    {
        List<Wuliu> list = wuliuService.selectWuliuList(wuliu);
        ExcelUtil<Wuliu> util = new ExcelUtil<Wuliu>(Wuliu.class);
        util.exportExcel(response, list, "物流信息数据");
    }

    /**
     * 获取物流信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:wuliu:query')")
    @GetMapping(value = "/{wuliuId}")
    public AjaxResult getInfo(@PathVariable("wuliuId") String wuliuId)
    {
        return success(wuliuService.selectWuliuByWuliuId(wuliuId));
    }

    /**
     * 新增物流信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:wuliu:add')")
    @Log(title = "物流信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Wuliu wuliu)
    {
        return toAjax(wuliuService.insertWuliu(wuliu));
    }

    /**
     * 修改物流信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:wuliu:edit')")
    @Log(title = "物流信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Wuliu wuliu)
    {
        return toAjax(wuliuService.updateWuliu(wuliu));
    }

    /**
     * 删除物流信息
     */
    @PreAuthorize("@ss.hasPermi('logistics:wuliu:remove')")
    @Log(title = "物流信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wuliuIds}")
    public AjaxResult remove(@PathVariable String[] wuliuIds)
    {
        return toAjax(wuliuService.deleteWuliuByWuliuIds(wuliuIds));
    }
}
