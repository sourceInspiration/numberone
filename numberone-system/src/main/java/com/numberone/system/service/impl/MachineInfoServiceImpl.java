package com.numberone.system.service.impl;

import com.numberone.common.exception.BusinessException;
import com.numberone.common.support.Convert;
import com.numberone.common.utils.DateUtils;
import com.numberone.common.utils.Md5Utils;
import com.numberone.common.utils.StringUtils;
import com.numberone.system.domain.MachineInfo;
import com.numberone.system.domain.request.PushMachineUseTimeReq;
import com.numberone.system.mapper.MachineInfoMapper;
import com.numberone.system.service.IMachineInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 设备 服务层实现
 *
 * @author lingyuan
 * @date 2019-05-12
 */
@Service
public class MachineInfoServiceImpl implements IMachineInfoService {

    private static final Logger log = LoggerFactory.getLogger(MachineInfoServiceImpl.class);

    private static final String MACHINE_TYPE = "kemu2";

    @Autowired
    private MachineInfoMapper machineInfoMapper;

    /**
     * 查询设备信息
     *
     * @param machineInfoId 设备ID
     * @return 设备信息
     */
    @Override
    public MachineInfo selectMachineInfoById(Integer machineInfoId) {
        MachineInfo machineInfo = machineInfoMapper.selectMachineInfoById(machineInfoId);
        if(machineInfo.getAuthDeadline()!=null){
            machineInfo.setAuthDeadlineString(DateUtils.dateTime(machineInfo.getAuthDeadline()));
        }
        return machineInfo;
    }

    /**
     * 查询设备列表
     *
     * @param machineInfo 设备信息
     * @return 设备集合
     */
    @Override
    public List<MachineInfo> selectMachineInfoList(MachineInfo machineInfo) {
        return machineInfoMapper.selectMachineInfoList(machineInfo);
    }

    /**
     * 新增设备
     *
     * @param machineInfo 设备信息
     * @return 结果
     */
    @Override
    public int insertMachineInfo(MachineInfo machineInfo) {
        if(MACHINE_TYPE.equals(machineInfo.getMachineType())){
            machineInfo.setMachineShortCode(Md5Utils.get12Char(machineInfo.getMachineCode()));
        }
        return machineInfoMapper.insertMachineInfo(machineInfo);
    }

    /**
     * 修改设备
     *
     * @param machineInfo 设备信息
     * @return 结果
     */
    @Override
    public int updateMachineInfo(MachineInfo machineInfo) {
        if(StringUtils.isNotEmpty(machineInfo.getAuthDeadlineString())){
            machineInfo.setAuthDeadline(DateUtils.parseDate(machineInfo.getAuthDeadlineString()));
        }else{
            machineInfo.setAuthDeadline(null);
        }
        //设置特定设备kemu2的短码
        if(MACHINE_TYPE.equals(machineInfo.getMachineType())){
            machineInfo.setMachineShortCode(Md5Utils.get12Char(machineInfo.getMachineCode()));
        }
        return machineInfoMapper.updateMachineInfo(machineInfo);
    }

    @Override
    public int grantMachines(MachineInfo machineInfo){
        return machineInfoMapper.grantMachines(machineInfo);
    }

    @Override
    public int cancelGrantMachines(MachineInfo machineInfo){
        return machineInfoMapper.cancelGrantMachines(machineInfo);
    }

    /**
     * 删除设备对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMachineInfoByIds(String ids) {
        return machineInfoMapper.deleteMachineInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 导入设备数据
     *
     * @param machineInfoList 设备数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importMachine(List<MachineInfo> machineInfoList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(machineInfoList) || machineInfoList.size() == 0) {
            throw new BusinessException("导入设备数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (MachineInfo machineInfo : machineInfoList) {
            try {
                // 验证是否存在这个设备信息
                MachineInfo queryMachine = new MachineInfo();
                queryMachine.setCompanyCode(machineInfo.getCompanyCode());
                queryMachine.setMachineCode(machineInfo.getMachineCode());
                List<MachineInfo> dbMachineList = machineInfoMapper.selectMachineInfoList(queryMachine);
                if (CollectionUtils.isEmpty(dbMachineList)) {
                    machineInfo.setCreateBy(operName);
                    machineInfo.setCreateTime(new Date());
                    if(MACHINE_TYPE.equals(machineInfo.getMachineType())){
                        machineInfo.setMachineShortCode(Md5Utils.get12Char(machineInfo.getMachineCode()));
                    }
                    this.insertMachineInfo(machineInfo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、设备机器码 " + machineInfo.getMachineCode() + " 导入成功");
                } else if (isUpdateSupport) {
                    machineInfo.setUpdateBy(operName);
                    machineInfo.setUpdateTime(new Date());
                    //设置特定设备kemu2的短码
                    if(MACHINE_TYPE.equals(machineInfo.getMachineType())){
                        machineInfo.setMachineShortCode(Md5Utils.get12Char(machineInfo.getMachineCode()));
                    }
                    this.updateMachineInfo(machineInfo);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、设备机器码 " + machineInfo.getMachineCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、设备机器码 " + machineInfo.getMachineCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + machineInfo.getMachineCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 查询设备信息
     *
     * @param machineCode 设备编号
     * @return 设备信息
     */
    @Override
    public MachineInfo selectMachineInfoByMachineCode(String machineCode) {
        return machineInfoMapper.selectMachineInfoByMachineCode(machineCode);
    }

    /**
     * 添加使用时长
     * @param pushMachinepUseTimeReq
     * @return
     */
    @Override
    public int addMachineUseTime(PushMachineUseTimeReq pushMachinepUseTimeReq){
        return machineInfoMapper.addMachineUseTime(pushMachinepUseTimeReq);
    }


}
