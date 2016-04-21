package org.tiger.framework.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JpaConfig
{
    
    /**
     * Transaction manager.
     *
     * @return the jpa transaction manager
     */
    @Bean
    public JpaTransactionManager transactionManager()
    {
        return new JpaTransactionManager();
    }
}
