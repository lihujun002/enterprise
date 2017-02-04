package org.tiger.framework;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.tiger.framework.log.LogsFilter;

@SpringBootApplication
public class Im4javaApplication
{
    
    /**
     * 打印请求日志
     * @return
     */
    @Bean(name="logsFilter")
    protected FilterRegistrationBean logFilterRegister(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        LogsFilter logsFilter = new LogsFilter();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(logsFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
    
    public static void main(String[] args)
    {
        SpringApplication.run(Im4javaApplication.class, args);
    }
}
