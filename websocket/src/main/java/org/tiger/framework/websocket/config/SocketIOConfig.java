package org.tiger.framework.websocket.config;

import java.util.HashMap;
import java.util.Map;

import org.tiger.framework.common.config.WebSocketProperties;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;

/**
 * websocket server 配置
 * 
 * @author lihj17
 *         
 */
public class SocketIOConfig
{
    private static Configuration configuration = new Configuration();
    
    private SocketIOConfig()
    {
    
    }
    
    /**
     * 获取配置信息
     * 
     * @param webSocketProperties
     * @return
     */
    public static Configuration getSocketIOConfig(WebSocketProperties webSocketProperties)
    {
        configuration.setHostname(webSocketProperties.getHost());
        configuration.setPort(webSocketProperties.getPort());
        configuration.setOrigin(webSocketProperties.getOrigin());
        return configuration;
    }
    
    /**
     * 保存连接的客户端
     * @author lihj17
     *
     */
    class SocketClient
    {
        private Map<String, SocketIOClient> socketClientList = new HashMap<String, SocketIOClient>();
        
        /**
         * 获取socket client
         * 
         * @param key
         * @return
         */
        public SocketIOClient get(String key)
        {
            return socketClientList.get(key);
        }
        
        /**
         * 设置 socket client
         * 
         * @param key
         * @param socketIOClient
         * @return
         */
        public SocketIOClient put(String key, SocketIOClient socketIOClient)
        {
            return socketClientList.put(key, socketIOClient);
        }
        
        /**
         * 删除指定 socket client
         * 
         * @param key
         * @return
         */
        public SocketIOClient remove(String key)
        {
            return socketClientList.remove(key);
        }
        
        /**
         * 删除所有 socket client
         */
        public void removeAll()
        {
            socketClientList.clear();
        }
    }
}
