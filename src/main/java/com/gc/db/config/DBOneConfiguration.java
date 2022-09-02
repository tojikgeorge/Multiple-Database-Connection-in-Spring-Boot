package com.gc.db.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:multiple-db.properties" })
@EnableJpaRepositories(
    basePackages = "com.gc.db.one", 
    entityManagerFactoryRef = "oneEntityManager", 
    transactionManagerRef = "oneTransactionManager")
public class DBOneConfiguration {
	
	@Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource oneDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	
	@Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean oneEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(oneDataSource());
        em.setPackagesToScan(
          new String[] { "com.gc.db.one" });

        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
  
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }
	
	@Primary
    @Bean
    public PlatformTransactionManager oneTransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		oneEntityManager().getObject());
        return transactionManager;
    }


}
