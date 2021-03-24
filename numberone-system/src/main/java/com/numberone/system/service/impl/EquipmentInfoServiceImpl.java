package com.numberone.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.system.mapper.EquipmentInfoMapper;
import com.numberone.system.domain.EquipmentInfo;
import com.numberone.system.service.IEquipmentInfoService;
import com.numberone.common.support.Convert;

/**
 * 设备 服务层实现
 * 
 * @author lingyuan
 * @date 2019-04-23
 */
@Service
public class EquipmentInfoServiceImpl implements IEquipmentInfoService 
{
	@Autowired
	private EquipmentInfoMapper equipmentInfoMapper;

	/**
     * 查询设备信息
     * 
     * @param equipmentInfoId 设备ID
     * @return 设备信息
     */
    @Override
	public EquipmentInfo selectEquipmentInfoById(Integer equipmentInfoId)
	{
	    return equipmentInfoMapper.selectEquipmentInfoById(equipmentInfoId);
	}
	
	/**
     * 查询设备列表
     * 
     * @param equipmentInfo 设备信息
     * @return 设备集合
     */
	@Override
	public List<EquipmentInfo> selectEquipmentInfoList(EquipmentInfo equipmentInfo)
	{
	    return equipmentInfoMapper.selectEquipmentInfoList(equipmentInfo);
	}
	
    /**
     * 新增设备
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
	@Override
	public int insertEquipmentInfo(EquipmentInfo equipmentInfo)
	{
	    return equipmentInfoMapper.insertEquipmentInfo(equipmentInfo);
	}
	
	/**
     * 修改设备
     * 
     * @param equipmentInfo 设备信息
     * @return 结果
     */
	@Override
	public int updateEquipmentInfo(EquipmentInfo equipmentInfo)
	{
	    return equipmentInfoMapper.updateEquipmentInfo(equipmentInfo);
	}

	/**
     * 删除设备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteEquipmentInfoByIds(String ids)
	{
		return equipmentInfoMapper.deleteEquipmentInfoByIds(Convert.toStrArray(ids));
	}
	
}
