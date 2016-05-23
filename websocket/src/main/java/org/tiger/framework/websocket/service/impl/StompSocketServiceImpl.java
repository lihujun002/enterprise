package org.tiger.framework.websocket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.tiger.framework.websocket.service.StompSocketService;

/**
 * 
 * @author lihj17
 *        
 */
@Service("stompSocketService")    
public class StompSocketServiceImpl implements StompSocketService
{
    @Autowired
    private SimpMessagingTemplate template;
    
    @Override
    public void sendMessage(String key, String message)
    {
        template.convertAndSend("/topic/hello/" + key, message);
    }
    
}
