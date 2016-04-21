/**
 * Copyright (C) 2013-2014 the original author or authors.
 */
package org.tiger.framework.common.cache;

/**
 * 
 * @author lihj17
 *        
 */
public @interface RedisCache
{
    
    public String expire();
    
    public String keyField();
}
