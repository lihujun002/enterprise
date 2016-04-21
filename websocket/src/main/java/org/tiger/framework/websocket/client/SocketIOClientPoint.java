package org.tiger.framework.websocket.client;

import java.util.HashMap;
import java.util.Map;

import com.corundumstudio.socketio.SocketIOClient;

public class SocketIOClientPoint
{
    private static Map<String, SocketIOClient> socketClientList = new HashMap<String, SocketIOClient>();
    
    /**
     * 获取socket client
     * 
     * @param key
     * @return
     */
    public static SocketIOClient get(String key)
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
    public static SocketIOClient put(String key, SocketIOClient socketIOClient)
    {
        return socketClientList.put(key, socketIOClient);
    }
    
    /**
     * 删除指定 socket client
     * 
     * @param key
     * @return
     */
    public static SocketIOClient remove(String key)
    {
        return socketClientList.remove(key);
    }
    
    /**
     * 删除所有 socket client
     */
    public static void removeAll()
    {
        socketClientList.clear();
    }
}
