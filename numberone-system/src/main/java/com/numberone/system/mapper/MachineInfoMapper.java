package com.numberone.system.mapper;

import com.numberone.system.domain.MachineInfo;
import com.numberone.system.domain.request.PushMachineUseTimeReq;

import java.util.List;

/**
 * 设备 数据层
 * 
 * @author lingyuan
 * @date 2019-05-12
 */
public interface MachineInfoMapper 
{
	/**
     * 查询设备信息
     *
     * @param machineInfoId 设备ID
     * @return 设备信息
     */
	public MachineInfo selectMachineInfoById(Integer machineInfoId);
	
	/**
     * 查询设备列表
     * 
     * @param machineInfo 设备信息
     * @return 设备集合
     */
	public List<MachineInfo> selectMachineInfoList(MachineInfo machineInfo);
	
	/**
     * 新增设备
     * 
     * @param machineInfo 设备信息
     * @return 结果
     */
	public int insertMachineInfo(MachineInfo machineInfo);
	
	/**
     * 修改设备
     * 
     * @param machineInfo 设备信息
     * @return 结果
     */
	public int updateMachineInfo(MachineInfo machineInfo);


	/**
	 * 授权设备
	 *
	 * @param machineInfo 设备信息
	 * @return 结果
	 */
	public int grantMachines(MachineInfo machineInfo);

	/**
	 * 取消授权设备
	 *
	 * @param machineInfo 设备信息
	 * @return 结果
	 */
	public int cancelGrantMachines(MachineInfo machineInfo);
	
	/**
     * 删除设备
     * 
     * @param machineInfoId 设备ID
     * @return 结果
     */
	public int deleteMachineInfoById(Integer machineInfoId);
	
	/**
     * 批量删除设备
     * 
     * @param machineInfoIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMachineInfoByIds(String[] machineInfoIds);

	/**
	 * 查询设备信息
	 *
	 * @param machineCode 设备编号
	 * @return 设备信息
	 */
	public MachineInfo selectMachineInfoByMachineCode(String machineCode);

	public int addMachineUseTime(PushMachineUseTimeReq pushMachinepUseTimeReq);


	
}