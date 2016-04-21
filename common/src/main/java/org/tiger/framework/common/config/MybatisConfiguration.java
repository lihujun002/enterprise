package org.tiger.framework.common.config;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConditionalOnClass({ EnableTransactionManagement.class, EntityManager.class })
@MapperScan(basePackages = { "org.tiger.framework.common.mapper" })
public class MybatisConfiguration implements EnvironmentAware {

	private static Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

	private RelaxedPropertyResolver propertyResolver;

	@Inject
	private DataSource dataSource;

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory() {
		try {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			sessionFactory.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
			sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources(propertyResolver.getProperty("mapperLocations")));
			return sessionFactory.getObject();
		} catch (Exception e) {
		    e.printStackTrace();
			logger.warn("Could not confiure mybatis session factory" + e.getMessage());
			return null;
		}
	}

	@Bean
	@ConditionalOnMissingBean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}