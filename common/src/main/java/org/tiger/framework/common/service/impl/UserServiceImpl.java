package org.tiger.framework.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.tiger.framework.common.entity.User;
import org.tiger.framework.common.mapper.UserMapper;
import org.tiger.framework.common.repository.UserRepository;
import org.tiger.framework.common.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    @Cacheable(value = "findAllUser", keyGenerator = "paramsKeyGenerator")
    public List<User> findAll()
    {
        logger.info("进入查询 findAll");
        // TODO Auto-generated method stub
        return userMapper.findAll();
    }
    
    @Override
    @Cacheable(value = "findByName", key = "'CACHE_USER_ACCOUNT_' + #userName")
    public User findByUserName(String userName)
    {
        logger.info("进入查询 findByUserName");
        return userMapper.findByUserName(userName);
    }
    
    @Override
    @Caching(
        evict = {@CacheEvict(value = "findByName", key = "'CACHE_USER_ACCOUNT_' + #oldCacheName"), // 清空key为旧name的缓存
            @CacheEvict(value = {"findById"}, key = "'CACHE_USER_ACCOUNT_' + #user.getId()"), // 清空key为id的缓存
            @CacheEvict(value = "findAllUser", allEntries = true)}) // 清空查询所有的缓存
    public int updateUser(User user, String oldCacheName)
    {
        logger.info("进入更新 updateUser");
        return userMapper.updateUser(user);
    }
    
    @Override
    @Cacheable(value = "findById", key = "'CACHE_USER_ACCOUNT_' + #id")
    public User findById(Integer id)
    {
        return userMapper.findById(id);
    }
    
}
