package org.tiger.framework.common.mapper;

import java.util.List;

import org.tiger.framework.common.entity.User;

/**
 * 用户数据处理
 * 
 * @author lihj17
 *        
 */
public interface UserMapper
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
    int updateUser(User user);
    
}
