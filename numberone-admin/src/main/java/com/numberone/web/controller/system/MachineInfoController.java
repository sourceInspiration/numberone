package com.numberone.web.controller.system;

import com.numberone.common.annotation.Log;
import com.numberone.common.base.AjaxResult;
import com.numberone.common.enums.BusinessType;
import com.numberone.common.page.TableDataInfo;
import com.numberone.common.utils.StringUtils;
import com.numberone.common.utils.poi.ExcelUtil;
import com.numberone.framework.util.ShiroUtils;
import com.numberone.framework.web.base.BaseController;
import com.numberone.system.domain.LisenceReq;
import com.numberone.system.domain.MachineInfo;
import com.numberone.system.domain.SysUser;
import com.numberone.system.domain.request.PushMachineUseTimeReq;
import com.numberone.system.domain.resp.BaseResp;
import com.numberone.system.domain.resp.LisenceResp;
import com.numberone.system.service.IMachineInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 设备 信息操作处理
 *
 * @author lingyuan
 * @date 2019-05-12
 */
@Controller
@RequestMapping("/system/machineInfo")
public class MachineInfoController extends BaseController {
    private String prefix = "system/machineInfo";

    private static final Logger log = LoggerFactory.getLogger(MachineInfoController.class);
    //可执行程序路径
    private static final String shPath = "/root/lingyuan880/jni/rsa1";
    //执行文件生成的文件根目录
    private static final String ROOT = "/root/";


    @Autowired
    private IMachineInfoService machineInfoService;

    @RequiresPermissions("system:machineInfo:view")
    @GetMapping()
    public String machineInfo() {
        return prefix + "/machineInfo";
    }

    /**
     * 查询设备列表
     */
    @RequiresPermissions("system:machineInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MachineInfo machineInfo) {
        startPage();
        List<MachineInfo> list = machineInfoService.selectMachineInfoList(machineInfo);
        return getDataTable(list);
    }


    /**
     * 导出设备列表
     */
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:machineInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MachineInfo machineInfo) {
        List<MachineInfo> list = machineInfoService.selectMachineInfoList(machineInfo);
        ExcelUtil<MachineInfo> util = new ExcelUtil<MachineInfo>(MachineInfo.class);

        return util.exportExcel(list, "machineInfo");
    }

    @Log(title = "设备管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:machineInfo:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<MachineInfo> util = new ExcelUtil<MachineInfo>(MachineInfo.class);
        List<MachineInfo> machineInfoList = util.importExcel(file.getInputStream());
        String userName = getSysUser().getLoginName();
        log.info("==========machineInfoList:", machineInfoList.toString());
        String message = machineInfoService.importMachine(machineInfoList, updateSupport, userName);
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:machineInfo:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<MachineInfo> util = new ExcelUtil<MachineInfo>(MachineInfo.class);
        return util.importTemplateExcel("设备数据");
    }

    /**
     * 新增设备
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存设备
     */
    @RequiresPermissions("system:machineInfo:add")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MachineInfo machineInfo) {
        SysUser user = getSysUser();
        machineInfo.setCreateBy(user.getUserId() + "");
        machineInfo.setCreateTime(new Date());
        machineInfo.setUpdateBy(user.getUserId() + "");
        machineInfo.setUpdateTime(new Date());
        return toAjax(machineInfoService.insertMachineInfo(machineInfo));
    }

    /**
     * 修改设备
     */
    @GetMapping("/edit/{machineInfoId}")
    public String edit(@PathVariable("machineInfoId") Integer machineInfoId, ModelMap mmap) {
        MachineInfo machineInfo = machineInfoService.selectMachineInfoById(machineInfoId);
        mmap.put("machineInfo", machineInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备
     */
    @RequiresPermissions("system:machineInfo:edit")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MachineInfo machineInfo) {
        SysUser user = getSysUser();
        machineInfo.setUpdateBy(user.getUserId() + "");
        machineInfo.setUpdateTime(new Date());
        return toAjax(machineInfoService.updateMachineInfo(machineInfo));
    }

    /**
     * 删除设备
     */
    @RequiresPermissions("system:machineInfo:remove")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(machineInfoService.deleteMachineInfoByIds(ids));
    }

    /**
     *
     */
    @Log(title = "查询公司授权码", businessType = BusinessType.OTHER)
    @PostMapping("/getMachineLicense")
    @ResponseBody
    public LisenceResp changeStatus(@RequestBody LisenceReq lisenceReq) {
        log.info("请求参数为getCompany:" + lisenceReq.getCompany() + "getMachineCode:" + lisenceReq.getMachineCode() + "getMachineType:" + lisenceReq.getMachineType());
        LisenceResp resp = new LisenceResp();
        if (StringUtils.isEmpty(lisenceReq.getMachineCode())) {
            resp.setMachine(lisenceReq.getMachineCode());
            resp.setResult("fail");
            resp.setMessageInfo("machineCode is null");
            return resp;
        } else if (StringUtils.isEmpty(lisenceReq.getMachineType())) {
            resp.setMachine(lisenceReq.getMachineCode());
            resp.setResult("fail");
            resp.setMessageInfo("machineType is null");
            return resp;
        }
        MachineInfo machineInfo = machineInfoService.selectMachineInfoByMachineCode(lisenceReq.getMachineCode());
        if (machineInfo != null && StringUtils.isNotEmpty(machineInfo.getLicense())) {
            if (!lisenceReq.getMachineType().equals(machineInfo.getMachineType())) {
                resp.setMachine(lisenceReq.getMachineCode());
                resp.setResult("fail");
                resp.setMessageInfo("the machineType is mismatching");
                return resp;
            }
            resp.setLicense(machineInfo.getLicense());
            resp.setMachine(lisenceReq.getMachineCode());
            resp.setResult("success");
            resp.setMessageInfo("success");
        } else if (machineInfo != null && StringUtils.isEmpty(machineInfo.getLicense())) {
            resp.setMachine(lisenceReq.getMachineCode());
            resp.setResult("fail");
            resp.setMessageInfo("the machineCode is not auth");
        } else if (machineInfo != null && 1 == machineInfo.getStatus()) {
            resp.setMachine(lisenceReq.getMachineCode());
            resp.setResult("fail");
            resp.setMessageInfo("the machineCode is not start using");
        } else {
            machineInfo = new MachineInfo();
            machineInfo.setCompanyCode(lisenceReq.getCompany());
            machineInfo.setMachineCode(lisenceReq.getMachineCode());
            machineInfo.setMachineType(lisenceReq.getMachineType());
            machineInfo.setStatus(0);
            machineInfo.setCreateBy("system");
            machineInfo.setCreateTime(new Date());
            machineInfo.setUpdateBy("system");
            machineInfo.setUpdateTime(new Date());
            machineInfoService.insertMachineInfo(machineInfo);
            resp.setMachine(lisenceReq.getMachineCode());
            resp.setResult("fail");
            resp.setMessageInfo("the machineCode is not exist");
        }
        return resp;
    }

    /**
     * 授权设备
     */
    @RequiresPermissions("system:machineInfo:grantMachines")
    @Log(title = "授权设备", businessType = BusinessType.GRANT)
    @PostMapping("/grantMachines")
    @ResponseBody
    public AjaxResult grantMachines(@RequestParam("ids[]") String[] ids) throws Exception {
        String errorMessage = null;
        log.info("============" + ids.toString());
        for (String machineInfoId : ids) {
            MachineInfo machineInfo = machineInfoService.selectMachineInfoById(Integer.parseInt(machineInfoId));
            if (machineInfo != null) {
                String license = getMachineLicense(machineInfo.getMachineCode());
                log.info("+++++++++++++++>" + license);
                if (StringUtils.isNotEmpty(license)) {
                    MachineInfo grantMachine = new MachineInfo();
                    grantMachine.setLicense(license);
                    grantMachine.setMachineInfoId(machineInfo.getMachineInfoId());
                    grantMachine.setUpdateBy(ShiroUtils.getLoginName());
                    grantMachine.setUpdateTime(new Date());
                    machineInfoService.grantMachines(grantMachine);
                }
            } else {
                errorMessage = "该机器码不存在" + machineInfoId;
            }
        }
        if (StringUtils.isNotEmpty(errorMessage)) {
            AjaxResult.error(errorMessage);
        }
        return AjaxResult.success("授权成功");
    }

    /**
     * 取消授权设备
     */
    @Log(title = "取消授权设备", businessType = BusinessType.GRANT)
    @PostMapping("/cancelGrantMachines")
    @ResponseBody
    public AjaxResult cancelGrantMachines(@RequestParam("ids[]") String[] ids) throws Exception {
        String errorMessage = null;
        for (String machineInfoId : ids) {
            log.info("============machineInfoId:" + machineInfoId);
            MachineInfo grantMachine = new MachineInfo();
            grantMachine.setMachineInfoId(Integer.parseInt(machineInfoId));
            grantMachine.setUpdateBy(ShiroUtils.getLoginName());
            grantMachine.setUpdateTime(new Date());
            machineInfoService.cancelGrantMachines(grantMachine);
        }

        return AjaxResult.success("取消授权成功");
    }

    /**
     *
     */
    @Log(title = "添加设备使用时间", businessType = BusinessType.UPDATE)
    @PostMapping("/addMachineUseTime")
    @ResponseBody
    public BaseResp addMachineUseTime(@RequestBody PushMachineUseTimeReq pushMachinepUseTimeReq) {
        log.info("请求参数为getCompanyCode:" + pushMachinepUseTimeReq.getCompanyCode() + "getMachineCode:" + pushMachinepUseTimeReq.getMachineCode() + "getMachineType:" + pushMachinepUseTimeReq.getMachineType());
        BaseResp resp = new BaseResp();
        if (StringUtils.isEmpty(pushMachinepUseTimeReq.getMachineCode())
                || StringUtils.isEmpty(pushMachinepUseTimeReq.getCompanyCode())
                || StringUtils.isEmpty(pushMachinepUseTimeReq.getMachineType())) {
            resp.setCode("fail");
            resp.setMessageInfo("parameter is null");
            return resp;
        }
        SysUser user = getSysUser();
        if (user != null) {
            pushMachinepUseTimeReq.setUpdateBy(user.getUserId() + "");
        } else {
            pushMachinepUseTimeReq.setUpdateBy("webService");
        }
        pushMachinepUseTimeReq.setUpdateTime(new Date());
        machineInfoService.addMachineUseTime(pushMachinepUseTimeReq);
        resp.setCode("success");
        resp.setMessageInfo("success");
        return resp;
    }

    private String getMachineLicense(String machineCode) throws Exception {
        String command1 = "chmod 777 " + shPath;
        Process process = null;
        process = Runtime.getRuntime().exec(command1);
        process.waitFor();
        Process p = null;
        String command2 = shPath + " " + machineCode;
        p = Runtime.getRuntime().exec(command2);
        if (p.waitFor() != 0) {
            if (p.exitValue() == 1) {
                log.info("命令执行失败==exitValue=============>" + p.exitValue());
            } else {
                log.info("命令执行失败==exitValue=============>" + p.exitValue());
            }
        } else {
            log.info("命令执行成功==waitFor=============>" + p.waitFor());
            log.info("命令执行成功==exitValue=============>" + p.exitValue());
            String licenseStr = readFile(machineCode);
            deleteFile(machineCode);
            return licenseStr;
        }
        return null;
    }

    /**
     * 读入TXT文件
     */
    public static String readFile(String fileName) throws IOException {
        StringBuilder licenses = new StringBuilder();
        String pathname = ROOT + fileName; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                licenses.append(line);
                //System.out.println(line);
            }
        } catch (IOException e) {
            log.error("readLicenseFile======error======", e);
            throw e;
        }
        return licenses.toString();
    }

    public static void deleteFile(String fileName) throws IOException {
        try {
            String pathname = ROOT + fileName;
            File file = new File(pathname);
            if (file.exists()) {
                file.delete();
            } else {
                log.info("文件不存在" + fileName);
            }
        } catch (Exception e) {
            log.error(fileName + "删除文件异常" + e);
        }
    }


}
