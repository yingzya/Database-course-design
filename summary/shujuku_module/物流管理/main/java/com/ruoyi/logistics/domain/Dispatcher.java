package com.ruoyi.logistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 调度员信息对象 dispatcher
 * 
 * @author yangwang
 * @date 2024-10-25
 */
public class Dispatcher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调度员编号 */
    private String dispatcherId;

    /** 调度员姓名 */
    @Excel(name = "调度员姓名")
    private String dispatcherName;

    /** 调度员电话 */
    @Excel(name = "调度员电话")
    private String dispatcherPhone;

    public void setDispatcherId(String dispatcherId) 
    {
        this.dispatcherId = dispatcherId;
    }

    public String getDispatcherId() 
    {
        return dispatcherId;
    }
    public void setDispatcherName(String dispatcherName) 
    {
        this.dispatcherName = dispatcherName;
    }

    public String getDispatcherName() 
    {
        return dispatcherName;
    }
    public void setDispatcherPhone(String dispatcherPhone) 
    {
        this.dispatcherPhone = dispatcherPhone;
    }

    public String getDispatcherPhone() 
    {
        return dispatcherPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dispatcherId", getDispatcherId())
            .append("dispatcherName", getDispatcherName())
            .append("dispatcherPhone", getDispatcherPhone())
            .toString();
    }
}
