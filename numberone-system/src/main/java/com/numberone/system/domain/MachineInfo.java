package com.numberone.system.domain;


import com.numberone.common.annotation.Excel;
import com.numberone.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 设备表 sys_machine_info
 * 
 * @author lingyuan
 * @date 2019-05-12
 */
public class MachineInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 设备id */
	private Integer machineInfoId;
	/** 设备机器码 */
	@Excel(name = "设备机器码")
	private String machineCode;
	/** 设备类型 */
	@Excel(name = "设备类型")
	private String machineType;
	@Excel(name = "设备机短码")
	private String machineShortCode;
	/** 公司编码 */
	@Excel(name = "公司编码")
	private String companyCode;
	/** 公司名称 */
	@Excel(name = "公司名称")
	private String companyName;
	/** 授权期限时间戳毫秒 */
	@Excel(name = "设备机器码授权期限")
	private Date authDeadline;
	/** 授权期限时间 */
	private String authDeadlineString;
	/** 许可字符串 */
	@Excel(name = "许可字符串")
	private String license;
	/** 状态（0正常 1停用） */
	@Excel(name = "状态（0正常 1停用）")
	private Integer status;
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

	public void setMachineInfoId(Integer machineInfoId) 
	{
		this.machineInfoId = machineInfoId;
	}

	public Integer getMachineInfoId() 
	{
		return machineInfoId;
	}
	public void setMachineCode(String machineCode) 
	{
		this.machineCode = machineCode;
	}

	public String getMachineCode() 
	{
		return machineCode;
	}
	public void setMachineType(String machineType) 
	{
		this.machineType = machineType;
	}

	public String getMachineType() 
	{
		return machineType;
	}
	public void setCompanyCode(String companyCode) 
	{
		this.companyCode = companyCode;
	}

	public String getCompanyCode() 
	{
		return companyCode;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setAuthDeadline(Date authDeadline)
	{
		this.authDeadline = authDeadline;
	}

	public Date getAuthDeadline()
	{
		return authDeadline;
	}
	public void setLicense(String license) 
	{
		this.license = license;
	}

	public String getLicense() 
	{
		return license;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
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

	public String getAuthDeadlineString() {
		return authDeadlineString;
	}

	public void setAuthDeadlineString(String authDeadlineString) {
		this.authDeadlineString = authDeadlineString;
	}

	public String getMachineShortCode() {
		return machineShortCode;
	}

	public void setMachineShortCode(String machineShortCode) {
		this.machineShortCode = machineShortCode;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("machineInfoId", getMachineInfoId())
            .append("machineCode", getMachineCode())
            .append("machineType", getMachineType())
            .append("companyCode", getCompanyCode())
            .append("companyName", getCompanyName())
            .append("authDeadline", getAuthDeadline())
            .append("license", getLicense())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
