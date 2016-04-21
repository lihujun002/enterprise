package org.tiger.framework.mq.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tiger.framework.common.config.RabbitMqProperties;
import org.tiger.framework.common.controller.BaseController;
import org.tiger.framework.common.exception.Code;
import org.tiger.framework.common.utils.ReqUtils;
import org.tiger.framework.common.vo.ResponseVO;
import org.tiger.framework.mq.producers.RabbitMqProducer;

/**
 * 发送信息
 * @author lihj17
 *
 */
@RestController
@RequestMapping("/v1/api")
public class MessageController extends BaseController
{
    @Resource
    RabbitMqProducer rabbitMqProducer;
    
    @Resource
    RabbitMqProperties rabbitMqProperties;
    
    @RequestMapping(value = "/rabbitmq/send", method = RequestMethod.GET)
    public Object sendMessage(HttpServletRequest request)
        throws Exception
    {
        String message = ReqUtils.getString(request, "message");
        rabbitMqProducer.send(rabbitMqProperties.getExchangeName(), rabbitMqProperties.getRoutingKey(), message);
        return new ResponseVO(Code.SUCCESS);
    }
}
