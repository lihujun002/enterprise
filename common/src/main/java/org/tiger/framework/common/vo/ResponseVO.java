package org.tiger.framework.common.vo;

import java.io.Serializable;

import org.tiger.framework.common.exception.Code;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 统一返回json数据格式
 * 
 * @author lihj17
 *        
 */
@JsonInclude(Include.NON_NULL)
public class ResponseVO implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 2603825707575811116L;
    
    protected String code;
    
    protected String msg;
    
    protected Object result;
    
    public ResponseVO()
    {
        this(Code.SUCCESS, null);
    }
    
    public ResponseVO(Object data)
    {
        this(Code.SUCCESS, data);
    }
    
    public ResponseVO(String code, String msg, Object data)
    {
        super();
        this.code = code;
        this.msg = msg;
        this.result = data;
    }
    
    public ResponseVO(String code, String msg)
    {
        super();
        this.code = code;
        this.msg = msg;
    }
    
    public ResponseVO(Code code, Object data)
    {
        this(code.getCode(), code.getMsg(), data);
    }
    
    public ResponseVO(Code code)
    {
        this(code.getCode(), code.getMsg(), null);
    }
    
    public String getcode()
    {
        return code;
    }
    
    public void setcode(String code)
    {
        this.code = code;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public Object getResult()
    {
        return result;
    }
    
    public void setResult(Object result)
    {
        this.result = result;
    }
    
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
    @Override
    public String toString()
    {
        return "response=  code=" + code + ", msg=" + msg + ", result=" + result + "";
    }
    
}
