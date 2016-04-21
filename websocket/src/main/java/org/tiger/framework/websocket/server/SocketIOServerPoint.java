package org.tiger.framework.websocket.server;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.tiger.framework.common.config.WebSocketProperties;
import org.tiger.framework.common.model.ChatObject;
import org.tiger.framework.websocket.client.SocketIOClientPoint;
import org.tiger.framework.websocket.config.SocketIOConfig;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

/**
 * WebSocket server端
 * 
 * @author lihj17
 *         
 */
@Component
public class SocketIOServerPoint
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    WebSocketProperties webSocketProperties;
    
    @PostConstruct
    public void startSocketIOServer()
    {
        Configuration config = SocketIOConfig.getSocketIOConfig(webSocketProperties);
        // 配置鉴权策略
        config.setAuthorizationListener(new AuthorizationListener()
        {
            
            @Override
            public boolean isAuthorized(HandshakeData data)
            {
                logger.info("连接前是否在此做鉴权操作呢？");
                return true;
            }
        });
        
        SocketIOServer server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener()
        {
            
            @Override
            public void onConnect(SocketIOClient client)
            {
                logger.info("有用户上线啦！ip:" + client.getRemoteAddress() + " sessionId:" + client.getSessionId());
                String userName = client.getHandshakeData().getSingleUrlParam("userName");
                client.set("userName", userName);
                // 连接上来后 加入在线列表
                SocketIOClientPoint.put(userName, client);
            }
        });
        
        server.addDisconnectListener(new DisconnectListener()
        {
            
            @Override
            public void onDisconnect(SocketIOClient client)
            {
                String userName = client.get("userName");
                logger.info("有用户闪人拉！ip:" + client.getRemoteAddress() + " sessionId:" + client.getSessionId()
                    + " userName:" + userName);
                // 从在线列表移除
                SocketIOClientPoint.remove(userName);
            }
        });
        
        server.addEventListener("message", ChatObject.class, new DataListener<ChatObject>()
        {
            @Override
            public void onData(final SocketIOClient client, ChatObject data, final AckRequest ackRequest)
            {
                String userName = client.get("userName");
                logger.info("收到来自ip:" + client.getRemoteAddress() + " sessionId:" + client.getSessionId() + " userName:"
                    + userName + " 的消息");
                logger.info(data.getMessage());
                ChatObject obj = new ChatObject("我是服务器", "你的信息已经收到!我再确认一下是不是: " + data.getMessage());
                client.sendEvent("message", obj);
            };
        });
        
        // 开启server
        server.start();
    }
}
