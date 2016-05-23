package org.tiger.framework.websocket.service;

/**
 * 
 * @author lihj17
 *
 */
public interface StompSocketService
{
    public void sendMessage(String key, String message);
}
