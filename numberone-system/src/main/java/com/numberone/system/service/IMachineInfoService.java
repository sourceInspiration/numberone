package com.numberone.system.service;

import com.numberone.system.domain.MachineInfo;
import com.numberone.system.domain.request.PushMachineUseTimeReq;

import java.util.List;

/**
 * 设备 服务层
 * 
 * @author lingyuan
 * @date 2019-05-12
 */
public interface IMachineInfoService 
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
	int grantMachines(MachineInfo machineInfo);

	/**
	 * 取消授权设备
	 *
	 * @param machineInfo 设备信息
	 * @return 结果
	 */
	int cancelGrantMachines(MachineInfo machineInfo);



	/**
     * 删除设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteMachineInfoByIds(String ids);

	/**
	 * 导入设备数据
	 *
	 * @param machineList 设备数据列表
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
	 * @param operName 操作用户
	 * @return 结果
	 */
	String importMachine(List<MachineInfo> machineList, Boolean isUpdateSupport, String operName);

	/**
	 * 查询设备信息
	 *
	 * @param machineCode 设备编码
	 * @return 设备信息
	 */
	MachineInfo selectMachineInfoByMachineCode(String machineCode);

	int addMachineUseTime(PushMachineUseTimeReq pushMachinepUseTimeReq);
	
}
