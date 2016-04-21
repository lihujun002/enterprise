package org.tiger.framework.common.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author lihj17
 *        
 * @param <T>
 */
public interface RedisService<T>
{
    public static final int DEFAULT_EXPIRE_TIME = 1;
    
    public static final int EXPIRE_TIME_5 = 5;
    
    public static final int EXPIRE_TIME_7 = 7;
    
    public static final int EXPIRE_TIME_15 = 15;
    
    public static final int EXPIRE_TIME_1 = 1;
    
    public static final int EXPIRE_TIME_2 = 2;
    
    public static final int EXPIRE_TIME_17 = 17;
    
    /**
     * simple class return
     * 
     * @param key
     * @param clz model vo class
     * @param <T>
     * @return Object
     */
    public <T> T get(final String key, final Class<T> clz);
    
    /**
     * simple class return
     * 
     * @param key
     * @param clz model vo class
     * @param <T>
     * @return Object
     */
    public <T> T getVal(final String key, final Class<T> clz);
    
    /**
     * through key find data collection --> Collection<T>
     * 
     * @param key
     * @param clz model vo class
     * @param collection convert collection
     * @param <T>
     * @return Collection
     */
    public <T> Collection<T> get(final String key, final Class<T> clz, Class<?> collection);
    
    public <T> Map<Long, T> getMap(String key, Class<T> clz);
    
    public <T> Map<String, T> getMapsByStr(String key, Class<T> clz);
    
    boolean isExist(String key);
    
    boolean delete(String key);
    
    /**
     * set key not expire
     * 
     * @param key
     * @param value
     * @return boolean
     */
    boolean set(String key, T value);
    
    /**
     * set key with liveTime
     * 
     * @param key
     * @param value
     * @param liveTime
     * @param timeUnit
     * @return boolean
     */
    boolean set(final String key, final T value, final long liveTime, TimeUnit timeUnit);
    
    boolean setNx(final String key, final T value);
    
    void set(Class<?> clazz, Object obj, String key);
    
    Boolean setTimeout(String key, TimeUnit timeUnit, int time);
    
    long generate(String key);
    
    void hmSet(String key, T value);
    
    void hSet(String key, String filed, T value);
    
    Map<Object, Object> hGetAll(String key);
    
    Object hGet(String key, String filed);
    
    Long sAdd(String key, String... strings);
    
    Set<String> sMembers(String key);
    
    Long srem(String key, String value);
}
