package com.ruoyi.employee.controller;

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
import com.ruoyi.employee.domain.employee;
import com.ruoyi.employee.service.IemployeeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工管理Controller
 * 
 * @author yangzy
 * @date 2024-10-21
 */
@RestController
@RequestMapping("/employee/employ")
public class employeeController extends BaseController
{
    @Autowired
    private IemployeeService employeeService;

    /**
     * 查询员工管理列表
     */
    @PreAuthorize("@ss.hasPermi('employee:employ:list')")
    @GetMapping("/list")
    public TableDataInfo list(employee employee)
    {
        startPage();
        List<employee> list = employeeService.selectemployeeList(employee);
        return getDataTable(list);
    }

    /**
     * 导出员工管理列表
     */
    @PreAuthorize("@ss.hasPermi('employee:employ:export')")
    @Log(title = "员工管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, employee employee)
    {
        List<employee> list = employeeService.selectemployeeList(employee);
        ExcelUtil<employee> util = new ExcelUtil<employee>(employee.class);
        util.exportExcel(response, list, "员工管理数据");
    }

    /**
     * 获取员工管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('employee:employ:query')")
    @GetMapping(value = "/{employeeId}")
    public AjaxResult getInfo(@PathVariable("employeeId") Long employeeId)
    {
        return success(employeeService.selectemployeeByEmployeeId(employeeId));
    }

    /**
     * 新增员工管理
     */
    @PreAuthorize("@ss.hasPermi('employee:employ:add')")
    @Log(title = "员工管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody employee employee)
    {
        return toAjax(employeeService.insertemployee(employee));
    }

    /**
     * 修改员工管理
     */
    @PreAuthorize("@ss.hasPermi('employee:employ:edit')")
    @Log(title = "员工管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody employee employee)
    {
        return toAjax(employeeService.updateemployee(employee));
    }

    /**
     * 删除员工管理
     */
    @PreAuthorize("@ss.hasPermi('employee:employ:remove')")
    @Log(title = "员工管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(employeeService.deleteemployeeByEmployeeIds(employeeIds));
    }
}
