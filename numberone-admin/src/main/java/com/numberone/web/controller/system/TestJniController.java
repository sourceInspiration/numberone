package com.numberone.web.controller.system;

import com.numberone.common.base.AjaxResult;
import com.numberone.framework.web.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 设备 信息操作处理
 * 
 * @author lingyuan
 * @date 2019-05-12
 */
@Controller
@RequestMapping("/system/testJni")
public class TestJniController extends BaseController
{
    private String prefix = "system/testJni";

	private static final Logger log = LoggerFactory.getLogger(TestJniController.class);


	@GetMapping("/getLicense/{number}")
	@ResponseBody
	public AjaxResult getLicense(@PathVariable("number") Long number ,
									 @RequestParam String cmdParameter , @RequestParam String parameter1)
	{

		String cmdTest = "./rsa1 00E06C76048";
		String[] cmd = new String[]{cmdParameter,parameter1};
		log.info("cmd---->"+cmdParameter+parameter1);
		Runtime run = Runtime.getRuntime();//返回与当前 Java 应用程序相关的运行时对象
		Process p = null;// 启动另一个进程来执行命令
		try {

			if(number==8){
				p = run.exec(cmdTest);
			}else{
				p = run.exec(cmd);
			}
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			//获得命令执行后在控制台的输出信息
			while ((lineStr = inBr.readLine()) != null) log.info("-------------->"+lineStr);
			// 打印输出信息  
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1)
					//p.exitValue()==0表示正常结束，1：非正常结束  
					log.info("-------------->"+"执行失败");
			}
			inBr.close();
			in.close();
		} catch (Exception e) {
			log.error("==========执行可执行程序异常",e);
			e.printStackTrace();
		}
		return AjaxResult.success("getLicense成功");
	}

	@GetMapping("/setLicense/{number}")
	@ResponseBody
	public AjaxResult setLicense(@PathVariable("number") Long number ,
									 @RequestParam String cmdParameter , @RequestParam String parameter1) throws Exception
	{
		try {
			log.info("cmd<--+++-->"+cmdParameter+parameter1);
			log.info("getSystemResource===============>"+this.getClass().getResource(""));
			String shPath=cmdParameter;//"/test/test.sh";   //程序路径
			Process process =null;
			String command1 = "chmod 777 " + shPath;
			process = Runtime.getRuntime().exec(command1);
			process.waitFor();
			Process p =null;
			String var=parameter1;//参数
			String command2 = shPath + " " + var;
			p = Runtime.getRuntime().exec(command2);
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			//获得命令执行后在控制台的输出信息
			while ((lineStr = inBr.readLine()) != null) {
				log.info("lineStr===============>"+lineStr);
			}
			// 打印输出信息  
			if (p.waitFor() != 0) {
				if (p.exitValue() == 1){
					log.info("命令执行失败==exitValue=============>"+p.exitValue());
				}else{
					log.info("命令执行失败==exitValue=============>"+p.exitValue());
				}
			}else{
				log.info("命令执行成功==waitFor=============>"+p.waitFor());
			}
			inBr.close();
			in.close();
			log.info("getSystemResource===============>"+this.getClass().getResource("/"));
		} catch (InterruptedException e) {
			log.error("InterruptedException==========执行可执行程序异常",e);
			return AjaxResult.error("InterruptedException");
		} catch (IOException e) {
			log.error("IOException==========执行可执行程序异常",e);
			return AjaxResult.error("IOException");
		}
		return AjaxResult.success("setLicense成功");
	}

}
