package org.tiger.framework.websocket.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tiger.framework.websocket.service.StompSocketService;

@Controller
public class StompSocketController
{
    private static Logger logger = LoggerFactory.getLogger(StompSocketController.class);
    
    @Resource
    private StompSocketService stompSocketService;
    
    @MessageMapping("/hello")
    @SendTo("/topic/hello/123")
    public Object greeting(GenericMessage message)
        throws Exception
    {
        Object obj = message.getPayload();
        logger.info("greeting 收到客户端消息:" + message.toString());
        if (obj instanceof byte[]) {
            byte[] bytes = (byte[])obj;
            return new String(bytes);
        }
        return obj.toString();
    }
    
    @MessageMapping("/message")
    @SendToUser("/message")
    public Object userMessage(String userMessage)
        throws Exception
    {
        logger.info("userMessage 收到客户端消息:" + userMessage);
        return "我已经收到你的消息";
    }
    
    
    @RequestMapping(path = "/sendMessage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String userMessage(HttpServletRequest request)
        throws Exception
    {
        String message = request.getParameter("message");
        stompSocketService.sendMessage("123", message);
        return "{OK}";
    }
}
