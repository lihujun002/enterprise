package org.tiger.framework.vo;

import java.io.Serializable;

public class ResponseVO implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 5949605653899388340L;
    
    private String msg;
    
    private String url;
    
    private Long times;
    
    public ResponseVO()
    {
        super();
    }
    
    public ResponseVO(String msg)
    {
        super();
        this.msg = msg;
    }
    
    public ResponseVO(String msg, String url)
    {
        super();
        this.msg = msg;
        this.url = url;
    }
    
    public ResponseVO(String msg, String url, Long times)
    {
        super();
        this.msg = msg;
        this.url = url;
        this.times = times;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public Long getTimes()
    {
        return times;
    }
    
    public void setTimes(Long times)
    {
        this.times = times;
    }
    
}
