package org.tiger.framework.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * websocket server 配置
 * 
 * @author lihj17
 *         
 */
@Component(value = "webSocketProperties")
@ConfigurationProperties(prefix = "websocket")
public class WebSocketProperties
{
    /**
     * websocket连接地址
     */
    private String host;
    
    /**
     * websocket连接端口
     */
    private Integer port;
    
    /**
     * 请求权限
     */
    private String origin;
    
    public String getHost()
    {
        return host;
    }
    
    public void setHost(String host)
    {
        this.host = host;
    }
    
    public Integer getPort()
    {
        return port;
    }
    
    public void setPort(Integer port)
    {
        this.port = port;
    }
    
    public String getOrigin()
    {
        return origin;
    }
    
    public void setOrigin(String origin)
    {
        this.origin = origin;
    }
    
}
