package com.ruoyi.employee.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.employee.mapper.employeeMapper;
import com.ruoyi.employee.domain.employee;
import com.ruoyi.employee.service.IemployeeService;

/**
 * 员工管理Service业务层处理
 * 
 * @author yangzy
 * @date 2024-10-21
 */
@Service
public class employeeServiceImpl implements IemployeeService 
{
    @Autowired
    private employeeMapper employeeMapper;

    /**
     * 查询员工管理
     * 
     * @param employeeId 员工管理主键
     * @return 员工管理
     */
    @Override
    public employee selectemployeeByEmployeeId(Long employeeId)
    {
        return employeeMapper.selectemployeeByEmployeeId(employeeId);
    }

    /**
     * 查询员工管理列表
     * 
     * @param employee 员工管理
     * @return 员工管理
     */
    @Override
    public List<employee> selectemployeeList(employee employee)
    {
        return employeeMapper.selectemployeeList(employee);
    }

    /**
     * 新增员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    @Override
    public int insertemployee(employee employee)
    {
        return employeeMapper.insertemployee(employee);
    }

    /**
     * 修改员工管理
     * 
     * @param employee 员工管理
     * @return 结果
     */
    @Override
    public int updateemployee(employee employee)
    {
        return employeeMapper.updateemployee(employee);
    }

    /**
     * 批量删除员工管理
     * 
     * @param employeeIds 需要删除的员工管理主键
     * @return 结果
     */
    @Override
    public int deleteemployeeByEmployeeIds(Long[] employeeIds)
    {
        return employeeMapper.deleteemployeeByEmployeeIds(employeeIds);
    }

    /**
     * 删除员工管理信息
     * 
     * @param employeeId 员工管理主键
     * @return 结果
     */
    @Override
    public int deleteemployeeByEmployeeId(Long employeeId)
    {
        return employeeMapper.deleteemployeeByEmployeeId(employeeId);
    }
}
