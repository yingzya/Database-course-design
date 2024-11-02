package com.ruoyi.employee.mapper;

import java.util.List;
import com.ruoyi.employee.domain.employee;

/**
 * 员工管理Mapper接口
 * 
 * @author yangzy
 * @date 2024-10-21
 */
public interface employeeMapper 
{
    /**
     * 查询员工管理
     * 
     * @param employeeId 员工管理主键
     * @return 员工管理
     */
    public employee selectemployeeByEmployeeId(Long employeeId);

    /**
     * 查询员工管理列表
     * 
     * @param employee 员工管理
     * @return 员工管理集合
     */
    public List<employee> selectemployeeList(employee employee);

    /**
     * 新增员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    public int insertemployee(employee employee);

    /**
     * 修改员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    public int updateemployee(employee employee);

    /**
     * 删除员工管理
     * 
     * @param employeeId 员工管理主键
     * @return 结果
     */
    public int deleteemployeeByEmployeeId(Long employeeId);

    /**
     * 批量删除员工管理
     * 
     * @param employeeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteemployeeByEmployeeIds(Long[] employeeIds);
}
