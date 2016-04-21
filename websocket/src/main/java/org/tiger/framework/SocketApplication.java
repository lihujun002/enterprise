package org.tiger.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.tiger.framework.common.config.WebSocketProperties;

@SpringBootApplication
@EnableConfigurationProperties({WebSocketProperties.class})
public class SocketApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SocketApplication.class, args);
    }
    
}
