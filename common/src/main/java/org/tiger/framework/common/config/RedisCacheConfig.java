package org.tiger.framework.common.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.tiger.framework.common.utils.MD5;
import org.tiger.framework.common.utils.SHA256;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 配置redis做为业务缓存
 * 
 * @author lihj17
 *         
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport
{
    
    /**
     * redis key 生成策略 MD5(className+methodName+params)
     * 
     * @return
     */
    @Bean
    public KeyGenerator paramsKeyGenerator()
    {
        return new KeyGenerator()
        {
            @Override
            public Object generate(Object target, Method method, Object... params)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params)
                {
                    sb.append(obj.toString());
                }
                String key = MD5.encrypt(sb.toString());
                return key;
            }
        };
        
    }
    
    @Bean
    public KeyGenerator primaryKeyGenerator()
    {
        return new KeyGenerator()
        {
            @Override
            public Object generate(Object target, Method method, Object... params)
            {
                StringBuilder sb = new StringBuilder();
                // sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params)
                {
                    sb.append(obj.toString());
                }
                String key = SHA256.encrypt(sb.toString());
                return key;
            }
        };
        
    }
    
    @Bean
    public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisConfigTemplate)
    {
        return new RedisCacheManager(redisConfigTemplate);
    }
    
    @Bean
    public RedisTemplate<String, String> redisConfigTemplate(RedisConnectionFactory factory)
    {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
