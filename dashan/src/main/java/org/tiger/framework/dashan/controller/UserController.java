package org.tiger.framework.dashan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiger.framework.common.cache.RedisService;
import org.tiger.framework.common.cache.cluster.JedisTemplate;
import org.tiger.framework.common.config.Properties;
import org.tiger.framework.common.controller.BaseController;
import org.tiger.framework.common.entity.User;
import org.tiger.framework.common.exception.Code;
import org.tiger.framework.common.service.UserService;
import org.tiger.framework.common.utils.Assert;
import org.tiger.framework.common.utils.ReqUtils;
import org.tiger.framework.common.vo.ResponseVO;

@RestController
@RequestMapping("/v1/api")
public class UserController extends BaseController
{
    @Resource
    private RedisService<Object> redisService;
    
    @Resource
    private JedisTemplate jedisTemplate;
    
    @Resource
    private UserService userService;
    
    @Resource
    private Properties properties;
    
    @RequestMapping(path = "/redis/get", produces = "application/json;charset=UTF-8")
    public Object getByRedis(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        String key = ReqUtils.getString(request, "key");
        
        // String value = redisService.getVal(key, String.class);
        
        String value = jedisTemplate.get(key);
        
        return value;
    }
    
    @RequestMapping(path = "/redis/set", produces = "application/json;charset=UTF-8")
    public Object setByRedis(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        String key = ReqUtils.getString(request, "key");
        String value = ReqUtils.getString(request, "value");
        // redisService.set(key, value);
        jedisTemplate.set(key, value);
        return "{OK}";
    }
    
    @RequestMapping(path = "/user/getAll", produces = "application/json;charset=UTF-8")
    public Object findAllUser(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        
        return userService.findAll();
    }
    
    @RequestMapping(path = "/user/getByUserName", produces = "application/json;charset=UTF-8")
    public Object findByUserName(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String userName = ReqUtils.getString(request, "userName");
        return userService.findByUserName(userName);
    }
    
    @RequestMapping(path = "/user/update", produces = "application/json;charset=UTF-8")
    public Object updateUser(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Integer id = ReqUtils.getInt(request, "id");
        String userName = ReqUtils.getString(request, "userName");
        String title = ReqUtils.getString(request, "title");
        String remark = ReqUtils.getString(request, "remark");
        
        Assert.hasText(id, Code.VALUE_ILLEGAL, "id");
        User oldUser = userService.findById(id);
        String oldUserName = oldUser.getName();
        oldUser.setName(userName);
        oldUser.setRemark(remark);
        oldUser.setTitle(title);
        int result = userService.updateUser(oldUser,oldUserName);
        if(result != 0)
            return new ResponseVO(Code.SUCCESS);
        else 
            return new ResponseVO(Code.ERROR);
    }
}
