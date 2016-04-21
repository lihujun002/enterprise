package org.tiger.framework.common.service;

import java.util.List;

import org.tiger.framework.common.entity.User;

public interface UserService
{
    /**
     * 获取所有用户
     * 
     * @return
     */
    List<User> findAll();
    
    /**
     * 根据用户名获取用户
     * 
     * @param userName
     * @return
     */
    User findByUserName(String userName);
    
    /**
     * 根据用户ID获取用户
     * 
     * @param userName
     * @return
     */
    User findById(Integer id);
    
    /**
     * 更新用户信息
     * 
     * @param user
     * @return
     */
    int updateUser(User user, String oldCacheName);
}
