package org.tiger.framework.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于记录所有请求日志
 * 
 * <pre>
 * 。
 * </pre>
 * 
 * @author lihujun hujun.li@midea.com.cn
 * @version 1.00.00
 *          
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class LogsFilter implements Filter
{
    
    private static Logger logger = LoggerFactory.getLogger(LogsFilter.class);
    
    /**
     * 打印请求参数
     * 
     * @param request
     */
    private void printParams(HttpServletRequest request, String path)
    {
        Enumeration<String> enumeration = request.getParameterNames();
        ArrayList<String> parameterList = Collections.list(enumeration);
        StringBuilder buildParams = new StringBuilder();
        for (String paramName : parameterList)
        {
            String value = request.getParameter(paramName);
            buildParams.append(paramName).append("=").append(value).append("&");
        }
        logger.info("ip:" + getIp(request) + "  request " + path + "  params:" + buildParams.toString());
        
    }
    
    /**
     * 获取IP
     */
    public String getIp(HttpServletRequest request)
    {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");
        
        String ip = null;
        if (realIp == null)
        {
            if (forwarded == null)
            {
                ip = remoteAddr;
            }
            else
            {
                ip = remoteAddr + "/" + forwarded;
            }
        }
        else
        {
            if (realIp.equals(forwarded))
            {
                ip = realIp;
            }
            else
            {
                ip = realIp + "/" + forwarded.replaceAll(", " + realIp, "");
            }
        }
        return ip;
    }
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        ((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
        HttpServletRequest req = (HttpServletRequest)request;
        String path = ((HttpServletRequest)request).getServletPath();
        printParams(req, path);// 打印请求参数
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        logger.info("LogsFilter destroy");
    }
}
