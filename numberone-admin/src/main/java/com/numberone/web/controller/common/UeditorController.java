package com.numberone.web.controller.common;

import com.baidu.ueditor.ActionEnter;
import com.numberone.framework.web.base.BaseController;
import com.numberone.web.controller.system.MachineInfoController;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 富文本操作处理
 *
 * @author lingyuan
 * @date 2021-03-12
 */
@Api(tags = "ueditor富文本编辑器后台配置")
@Controller
@RequestMapping("/system/ueditor")
public class UeditorController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MachineInfoController.class);

    private static final String configFileName = "config.json";


    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");

        response.setHeader("Content-Type", "text/html");


        ApplicationHome home = new ApplicationHome(getClass());// jar包所在路径

        String rootPath = home.getSource().getAbsolutePath();

        OutputStream out = null;

        try {

// 判断配置文件是否存在，再确定根路径

            File file = new File(rootPath + File.separator + configFileName);

            if (!file.exists()) {// 文件不存在，从config目录下查找

                file = new File(rootPath);

                rootPath = file.getParent() + File.separator + "config";

            }

            logger.info("UeditorController.index>>>>>>>>>>>>>>>{}", rootPath);


            out = response.getOutputStream();

            out.write(new ActionEnter(request, rootPath + File.separator).exec().getBytes("utf-8"));

            out.flush();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (out != null) {

                out.close();

                out = null;

            }

        }

    }


    /**
     * 这里不是上传，而是重新定义ueditor的下载路径
     */

    @RequestMapping("/upload/**")

    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uri = request.getRequestURI();

        logger.info("UeditorController.upload>>>>>>>>>>>>>>>{}", uri);

        response.setContentType("application/x-msdownload");

        OutputStream out = null;

        FileInputStream in = null;

        ByteArrayOutputStream bos = null;

        try {

// 获取文件信息

            ApplicationHome home = new ApplicationHome(getClass());// jar包所在路径

            String rootPath = home.getSource().getAbsolutePath();

// 判断配置文件是否存在，再确定根路径

            File configFile = new File(rootPath + File.separator + configFileName);

            if (!configFile.exists()) {// 文件不存在，从config目录下查找

                configFile = new File(rootPath);

                rootPath = configFile.getParent() + File.separator + "config";

            }


// 把文件转换成字节

            File file = new File(rootPath + uri);

            in = new FileInputStream(file);

            bos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024 * 4];

            int n = 0;

            while ((n = in.read(buffer)) != -1) {

                bos.write(buffer, 0, n);

            }


// 输出数据

            String fileName = file.getName();

// logger.info(">>>>>>>>>>{}", request.getHeader("User-Agent"));

            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {

                fileName = URLEncoder.encode(fileName, "utf-8");

            } else {

                fileName = new String(fileName.getBytes("utf-8"), "iso8859-1");

            }

            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");

            out = response.getOutputStream();


            out.write(bos.toByteArray());

            out.flush();

        } catch (Exception e) {

            logger.error("UeditorController.upload error", e);

        } finally {

            if (bos != null) {

                try {

                    bos.close();

                } catch (IOException e) {

                    logger.error("<<<<<<<<UeditorController.download ByteArrayOutputStream关闭发生异常!{}>>>>>>>>", e);

                }

            }

            if (in != null) {

                try {

                    in.close();

                } catch (IOException e) {

                    logger.error("<<<<<<<<UeditorController.upload FileInputStream关闭发生异常!{}>>>>>>>>", e);

                }

            }

            if (out != null) {

                try {

                    out.close();

                } catch (IOException e) {

                    logger.error("<<<<<<<<FileController.upload文件输出流关闭发生异常!{}>>>>>>>>", e);

                }

            }

        }

    }
}
