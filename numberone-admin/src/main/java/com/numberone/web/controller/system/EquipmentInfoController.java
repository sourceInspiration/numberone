package com.numberone.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.numberone.common.annotation.Log;
import com.numberone.common.enums.BusinessType;
import com.numberone.system.domain.EquipmentInfo;
import com.numberone.system.service.IEquipmentInfoService;
import com.numberone.framework.web.base.BaseController;
import com.numberone.common.page.TableDataInfo;
import com.numberone.common.base.AjaxResult;
import com.numberone.common.utils.poi.ExcelUtil;

/**
 * 设备 信息操作处理
 * 
 * @author lingyuan
 * @date 2019-04-23
 */
@Controller
@RequestMapping("/system/equipmentInfo")
public class EquipmentInfoController extends BaseController
{
    private String prefix = "system/equipmentInfo";
	
	@Autowired
	private IEquipmentInfoService equipmentInfoService;
	
	@RequiresPermissions("system:equipmentInfo:view")
	@GetMapping()
	public String equipmentInfo()
	{
	    return prefix + "/equipmentInfo";
	}
	
	/**
	 * 查询设备列表
	 */
	@RequiresPermissions("system:equipmentInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(EquipmentInfo equipmentInfo)
	{
		startPage();
        List<EquipmentInfo> list = equipmentInfoService.selectEquipmentInfoList(equipmentInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出设备列表
	 */
	@RequiresPermissions("system:equipmentInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EquipmentInfo equipmentInfo)
    {
    	List<EquipmentInfo> list = equipmentInfoService.selectEquipmentInfoList(equipmentInfo);
        ExcelUtil<EquipmentInfo> util = new ExcelUtil<EquipmentInfo>(EquipmentInfo.class);
        return util.exportExcel(list, "equipmentInfo");
    }
	
	/**
	 * 新增设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备
	 */
	@RequiresPermissions("system:equipmentInfo:add")
	@Log(title = "设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(EquipmentInfo equipmentInfo)
	{		
		return toAjax(equipmentInfoService.insertEquipmentInfo(equipmentInfo));
	}

	/**
	 * 修改设备
	 */
	@GetMapping("/edit/{equipmentInfoId}")
	public String edit(@PathVariable("equipmentInfoId") Integer equipmentInfoId, ModelMap mmap)
	{
		EquipmentInfo equipmentInfo = equipmentInfoService.selectEquipmentInfoById(equipmentInfoId);
		mmap.put("equipmentInfo", equipmentInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备
	 */
	@RequiresPermissions("system:equipmentInfo:edit")
	@Log(title = "设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(EquipmentInfo equipmentInfo)
	{		
		return toAjax(equipmentInfoService.updateEquipmentInfo(equipmentInfo));
	}
	
	/**
	 * 删除设备
	 */
	@RequiresPermissions("system:equipmentInfo:remove")
	@Log(title = "设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(equipmentInfoService.deleteEquipmentInfoByIds(ids));
	}
	
}
