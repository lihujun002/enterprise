package org.tiger.framework.common.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.tiger.framework.common.cache.cluster.CacheFieldAnnotation;
import org.tiger.framework.common.exception.AppException;
import org.tiger.framework.common.utils.ClassHelper;
import org.tiger.framework.common.utils.JsonUtil;

/**
 * 
 * @author lihj17
 *         
 * @param <T>
 */
@Service("redisService")
public class RedisServiceImpl<T> implements RedisService<T>
{
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Override
    public <T> T get(final String key, final Class<T> clz)
    {
        Map<Object, Object> map = this.hGetAll(key);
        if (null != map && !map.isEmpty())
        {
            try
            {
                return populate(map, clz);
            }
            catch (Exception e)
            {
                logger.error("数据转换发生异常：" + e);
                throw new AppException("数据转换发生异常：" + e);
            }
        }
        return null;
    }
    
    @Override
    public <T> T getVal(final String key, final Class<T> clz)
    {
        try
        {
            if (StringUtils.isBlank(key))
            {
                if (logger.isWarnEnabled())
                {
                    logger.warn("get redis Cache by key: null.");
                }
                return null;
            }
            String jsonData = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(jsonData))
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("Get redis Cache, key: {}, value: {}", key, jsonData);
                }
                return JsonUtil.fromStr(jsonData, clz);
            }
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("get redis data error key : {} ,reason : {} ", key, e);
            }
            throw e;
        }
        return null;
    }
    
    @Override
    public <T> Collection<T> get(String key, Class<T> clz, Class<?> collection)
    {
        try
        {
            if (StringUtils.isBlank(key))
            {
                if (logger.isWarnEnabled())
                {
                    logger.warn("get redis Cache by key: null.");
                }
                return null;
            }
            String jsonData = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(jsonData))
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("Get redis Cache, key: {}, value: {}", key, jsonData);
                }
                if (collection != null)
                {
                    return JsonUtil.getCollectionFromStr(jsonData, clz, collection);
                }
            }
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("get redis data error key : {} ,reason : {} ", key, e);
            }
            throw e;
        }
        return null;
    }
    
    @Override
    public boolean isExist(String key)
    {
        try
        {
            boolean isExist = redisTemplate.hasKey(key);
            if (logger.isDebugEnabled())
            {
                logger.debug("isExist redis cache, key: {}, exist: {}", key, isExist);
            }
            return isExist;
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("redis has a problem key : {} ,reason: {} ", key, e);
            }
            throw e;
        }
    }
    
    @Override
    public boolean delete(String key)
    {
        try
        {
            redisTemplate.delete(key);
            logger.debug("DELETE redis cache key: {} SUCCESS", key);
        }
        catch (Exception e)
        {
            logger.error("redis delete key : {} error ,reason: {} ", key, e);
        }
        return true;
    }
    
    @Override
    public boolean set(final String key, final T value)
    {
        if (StringUtils.isBlank(key))
        {
            if (logger.isWarnEnabled())
            {
                logger.warn("set redis cache the null. key: {}, value: {} ", key, value);
            }
            return false;
        }
        String valueJson = null;
        try
        {
            valueJson = JsonUtil.toJson(value);
            redisTemplate.opsForValue().set(key, valueJson);
            if (logger.isDebugEnabled())
            {
                logger.debug("set redis cache key: {}, value: {}", key, valueJson);
            }
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("set key:{} error, reason : {} ", key, e);
            }
            throw e;
        }
        return true;
    }
    
    @Override
    public boolean set(final String key, final T value, final long liveTime, TimeUnit timeUnit)
    {
        if (StringUtils.isBlank(key) || null == value)
        {
            if (logger.isWarnEnabled())
            {
                logger.warn("set redis cache the null. key: {}, value: {}, exp: {}", key, value, liveTime);
            }
            return false;
        }
        String valueJson = null;
        try
        {
            valueJson = JsonUtil.toJson(value);
            redisTemplate.opsForValue().set(key, valueJson, liveTime, timeUnit);
            if (logger.isDebugEnabled())
            {
                logger.debug("set redis cache key: {}, liveTime: {} , value: {}", key, liveTime, valueJson);
            }
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("set key:{}, value: {}, error reason: {} ", key, valueJson, e);
            }
            throw e;
        }
        return true;
    }
    
    @Override
    public boolean setNx(final String key, final T value)
    {
        String valueJson = null;
        try
        {
            valueJson = JsonUtil.toJson(value);
            return redisTemplate.opsForValue().setIfAbsent(key, valueJson);
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("set key:{}, value: {}, error reason: {} ", key, valueJson, e);
            }
            throw e;
        }
    }
    
    @Override
    public long generate(String key)
    {
        return 1000 + redisTemplate.opsForValue().increment(key, 1);
    }
    
    @Override
    public Boolean setTimeout(String key, TimeUnit timeUnit, int time)
    {
        return redisTemplate.expire(key, time, timeUnit);
    }
    
    @Override
    public <T> Map<Long, T> getMap(String key, Class<T> clz)
    {
        try
        {
            if (StringUtils.isBlank(key))
            {
                if (logger.isWarnEnabled())
                {
                    logger.warn("get redis Cache by key: null.");
                }
                return null;
            }
            String jsonData = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(jsonData))
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("Get redis Cache, key: {}, value: {}", key, jsonData);
                }
                return JsonUtil.getMap(jsonData, clz);
            }
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("get redis data error key : {} ,reason : {} ", key, e);
            }
            throw e;
        }
        return null;
    }
    
    @Override
    public <T> Map<String, T> getMapsByStr(String key, Class<T> clz)
    {
        try
        {
            if (StringUtils.isBlank(key))
            {
                if (logger.isWarnEnabled())
                {
                    logger.warn("get redis Cache by key: null.");
                }
                return null;
            }
            String jsonData = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(jsonData))
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug("Get redis Cache, key: {}, value: {}", key, jsonData);
                }
                return JsonUtil.getMapsByStr(jsonData, clz);
            }
        }
        catch (DataAccessException e)
        {
            if (logger.isErrorEnabled())
            {
                logger.error("get redis data error key : {} ,reason : {} ", key, e);
            }
            throw e;
        }
        return null;
    }
    
    /**
     * 将对象存入Redis
     * 
     * @param clazz
     * @param obj
     * @param key
     * @throws Exception
     */
    public void set(Class<?> clazz, Object obj, String key)
    {
        Map<String, String> redisMap = getFieldValueMap(clazz, obj);
        redisTemplate.opsForHash().putAll(key, (Map<String, String>)redisMap);
    }
    
    private List<Field> getClassFields(List<Field> fields, Class<?> clazz)
    {
        Field[] fs = clazz.getDeclaredFields();
        for (Field field : fs)
        {
            fields.add(field);
        }
        if (clazz.getSuperclass() != null)
        {
            getClassFields(fields, clazz.getSuperclass());
        }
        return fields;
    }
    
    private Map<String, String> getFieldValueMap(Class<?> clazz, Object obj)
    {
        List<Field> fList = new ArrayList<Field>();
        fList = getClassFields(fList, clazz);
        Map<String, String> redisMap = new HashMap<String, String>();
        if (fList.size() > 0)
        {
            String value;
            CacheFieldAnnotation cacheField;
            for (Field field : fList)
            {
                cacheField = field.getAnnotation(CacheFieldAnnotation.class);
                if (cacheField != null)
                {
                    if (cacheField.cache() == false)
                        continue;
                }
                
                value = ClassHelper.getStringValue(field, obj);
                if (value != null)
                {
                    redisMap.put(field.getName(), value);
                }
            }
            return redisMap;
        }
        else
        {
            throw new RuntimeException("the saving entity not found declarField!");
        }
    }
    
    private <T> T populate(Map<Object, Object> map, Class<T> clazz)
        throws InstantiationException, IllegalAccessException
    {
        T obj = clazz.newInstance();
        List<Field> fList = new ArrayList<Field>();
        fList = getClassFields(fList, clazz);
        if (fList.size() > 0)
        {
            CacheFieldAnnotation cacheField;
            for (Field field : fList)
            {
                if ("serialVersionUID".equals(field.getName()) || Modifier.isFinal(field.getModifiers()))
                {
                    continue;
                }
                cacheField = field.getAnnotation(CacheFieldAnnotation.class);
                
                if (cacheField != null)
                {
                    if (cacheField.cache())
                    {
                        if (cacheField.keyField() == false && !StringUtils.isEmpty(cacheField.cacheKey()))
                        {
                            ClassHelper.setStringValue(field, obj, map.get(cacheField.cacheKey()));
                        }
                    }
                    else
                    {
                        continue;
                    }
                }
                else
                {
                    ClassHelper.setStringValue(field, obj, map.get(field.getName()));
                }
            }
            
        }
        return obj;
    }
    
    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中
     */
    public void hmSet(String key, T value)
    {
        redisTemplate.opsForHash().putAll(key, (Map<String, Object>)value);
    }
    
    /**
     * 设置 指定 filed 的值,将覆盖旧值
     */
    public void hSet(String key, String filed, T value)
    {
        redisTemplate.opsForHash().put(key, filed, value);
    }
    
    /**
     * 获取全部Hash 域和值
     */
    public Map<Object, Object> hGetAll(String key)
    {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return map;
    }
    
    /**
     * 获取指定字段的值
     */
    public Object hGet(String key, String filed)
    {
        return redisTemplate.opsForHash().get(key, filed);
    }
    
    /**
     * 添加一个或多个指定的values元素到 key对应的集合中 返回添加成功的元素数量,不包括已存在的
     */
    @Override
    public Long sAdd(String key, String... values)
    {
        return redisTemplate.opsForSet().add(key, values);
    }
    
    /**
     * 返回 key 对应集合的全部元素
     */
    @Override
    public Set<String> sMembers(String key)
    {
        return redisTemplate.opsForSet().members(key);
    }
    
    /**
     * 删除 key 对应集合中 指定的 value 元素
     */
    public Long srem(String key, String value)
    {
        return redisTemplate.opsForSet().remove(key, value);
    }
}
