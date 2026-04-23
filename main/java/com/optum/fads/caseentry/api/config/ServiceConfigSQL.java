package com.optum.fads.caseentry.api.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

/**
 * @author sbajaj8
 *
 */
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "sqlEntityManagerFactory",
						transactionManagerRef = "txManagerSQL",
						basePackages = "com.optum.fads.caseentry.api.repo" )
		public class ServiceConfigSQL 
		{
			@Bean(name = "fadsSQLDataSource")
			@ConfigurationProperties("spring.datasource.sql") 
				public DataSource fadsSQLDataSource() {
					return DataSourceBuilder.create().build(); 
			}
	
			@Bean(name = "sqlEntityManagerFactory")
				public LocalContainerEntityManagerFactoryBean sqlEntityManagerFactory()
					{
						LocalContainerEntityManagerFactoryBean em
								= new LocalContainerEntityManagerFactoryBean();
						em.setDataSource(fadsSQLDataSource());
						em.setPackagesToScan(
								new String[] { "com.optum.fads.caseentry.api.domain" });
						HibernateJpaVendorAdapter vendorAdapter
							= new HibernateJpaVendorAdapter();
						em.setJpaVendorAdapter(vendorAdapter);
					return em;
					}
	 
	 
	 @Bean(name = "txManagerSQL")
	 public PlatformTransactionManager txManagerSQL(@Qualifier("sqlEntityManagerFactory")EntityManagerFactory sqlEntityManagerFactory)
	 {
		 JpaTransactionManager txManager = new JpaTransactionManager();
	        txManager.setEntityManagerFactory(sqlEntityManagerFactory().getObject());
	        return txManager;
	 }
	 
	}


	

