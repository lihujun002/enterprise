package org.tiger.framework.dashan.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tiger.framework.common.exception.AppException;
import org.tiger.framework.common.exception.Code;
import org.tiger.framework.common.vo.ResponseVO;

@RestController
public class BaseController
{
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @ExceptionHandler
    @ResponseBody
    public Object handleException(Exception e, HttpServletRequest request)
    {
        logger.error("BaseController exception:{}", e);
        if (e instanceof AppException)
        {
            AppException ex = (AppException)e;
            return new ResponseVO(ex.getErrorCode().getCode(), ex.getMsg());
        }
        return new ResponseVO(Code.ERROR.getCode(), e.getMessage());
    }
}
