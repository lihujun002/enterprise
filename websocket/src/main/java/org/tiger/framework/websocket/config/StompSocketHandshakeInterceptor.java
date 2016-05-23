package org.tiger.framework.websocket.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 
 * @author lihj17
 *        
 */
@Component
public class StompSocketHandshakeInterceptor implements HandshakeInterceptor
{
    
    private static Logger logger = LoggerFactory.getLogger(StompSocketHandshakeInterceptor.class);
    
    /**
     * 握手之前
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
        Map<String, Object> attributes)
            throws Exception
    {
        logger.info("握手前鉴权.....");
        boolean flag = false;
        if (request instanceof ServletServerHttpRequest)
        {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
            logger.info("拿到request:"+servletRequest);
        }
        return true;
    }
    
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
        Exception exception)
    {
    
    }
}
