package com.ruoyi.employee.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工管理对象 employee_management
 * 
 * @author yangzy
 * @date 2024-10-21
 */
public class employee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工工号 */
    private Long employeeId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String employeeName;

    /** 员工账号 */
    @Excel(name = "员工账号")
    private String account;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phoneNumber;

    /** 账号状态 */
    @Excel(name = "账号状态")
    private Long accountStatus;

    /** 最后操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOperationTime;

    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
    }
    public void setEmployeeName(String employeeName) 
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() 
    {
        return employeeName;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setAccountStatus(Long accountStatus) 
    {
        this.accountStatus = accountStatus;
    }

    public Long getAccountStatus() 
    {
        return accountStatus;
    }
    public void setLastOperationTime(Date lastOperationTime) 
    {
        this.lastOperationTime = lastOperationTime;
    }

    public Date getLastOperationTime() 
    {
        return lastOperationTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("employeeId", getEmployeeId())
            .append("employeeName", getEmployeeName())
            .append("account", getAccount())
            .append("phoneNumber", getPhoneNumber())
            .append("accountStatus", getAccountStatus())
            .append("lastOperationTime", getLastOperationTime())
            .toString();
    }
}
