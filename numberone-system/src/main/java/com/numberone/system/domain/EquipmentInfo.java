package com.numberone.system.domain;


import com.numberone.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 设备表 sys_equipment_info
 * 
 * @author lingyuan
 * @date 2019-04-23
 */
public class EquipmentInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 设备id */
	private Integer equipmentInfoId;
	/** 设备编码 */
	private String equipmentCode;
	/** 设备授权码 */
	private String authorizationCode;
	/** 运行时间毫秒 */
	private Long runTime;
	/** 状态（0正常 1停用） */
	private String status;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;

	public void setEquipmentInfoId(Integer equipmentInfoId) 
	{
		this.equipmentInfoId = equipmentInfoId;
	}

	public Integer getEquipmentInfoId() 
	{
		return equipmentInfoId;
	}
	public void setEquipmentCode(String equipmentCode) 
	{
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentCode() 
	{
		return equipmentCode;
	}
	public void setAuthorizationCode(String authorizationCode) 
	{
		this.authorizationCode = authorizationCode;
	}

	public String getAuthorizationCode() 
	{
		return authorizationCode;
	}
	public void setRunTime(Long runTime) 
	{
		this.runTime = runTime;
	}

	public Long getRunTime() 
	{
		return runTime;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentInfoId", getEquipmentInfoId())
            .append("equipmentCode", getEquipmentCode())
            .append("authorizationCode", getAuthorizationCode())
            .append("runTime", getRunTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
