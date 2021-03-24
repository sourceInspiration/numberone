package com.numberone.system.mapper;

import com.numberone.system.domain.EquipmentInfo;
import java.util.List;	

/**
 * 设备 数据层
 * 
 * @author lingyuan
 * @date 2019-04-23
 */
public interface EquipmentInfoMapper 
{
	/**
     * 查询设备信息
     * 
     * @param equipmentInfoId 设备ID
     * @return 设备信息
     */
	public EquipmentInfo selectEquipmentInfoById(Integer equipmentInfoId);
	
	/**
     * 查询设备列表
     * 
     * @param equipmentInfo 设备信息
     * @return 设备集合
     */
	public List<EquipmentInfo> selectEquipmentInfoList(EquipmentInfo equipmentInfo);
	
	/**
     * 新增设备
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
	public int insertEquipmentInfo(EquipmentInfo equipmentInfo);
	
	/**
     * 修改设备
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
	public int updateEquipmentInfo(EquipmentInfo equipmentInfo);
	
	/**
     * 删除设备
     * 
     * @param equipmentInfoId 设备ID
     * @return 结果
     */
	public int deleteEquipmentInfoById(Integer equipmentInfoId);
	
	/**
     * 批量删除设备
     * 
     * @param equipmentInfoIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteEquipmentInfoByIds(String[] equipmentInfoIds);
	
}