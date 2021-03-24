package com.numberone.framework.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.numberone.common.utils.ServletUtils;

/**
 * 服务相关配置
 * 
 * @author numberone
 *
 */
@Component
public class ServerConfig
{

    private static final Logger log = LoggerFactory.getLogger(ServerConfig.class);

    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     * 
     * @return 服务地址
     */
    public String getUrl()
    {
        HttpServletRequest request = ServletUtils.getRequest();
        return getDomain(request);
    }

    public static String getDomain(HttpServletRequest request)
    {
        StringBuffer url = request.getRequestURL();
        log.info("===url==:{}",url);
        String contextPath = request.getServletContext().getContextPath();
        log.info("===contextPath==:{}",contextPath);
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
