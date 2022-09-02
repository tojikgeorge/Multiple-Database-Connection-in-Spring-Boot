package com.gc.db.config;

import java.util.HashMap;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:multiple-db.properties" })
@EnableJpaRepositories(
    basePackages = "com.gc.db.two", 
    entityManagerFactoryRef = "twoEntityManager", 
    transactionManagerRef = "twoTransactionManager")
public class DBTwoConfiguration {
	
		
    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource twoDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	
	@Bean("twoEntityManager")
    public LocalContainerEntityManagerFactoryBean twoEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(twoDataSource());
        em.setPackagesToScan("com.gc.db.two.entity" );

        HibernateJpaVendorAdapter vendorAdapter
          = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
  
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }
	
    @Bean("twoTransactionManager")
    public PlatformTransactionManager twoTransactionManager() {
 
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		twoEntityManager().getObject());
        return transactionManager;
    }
	
	
	@Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(twoDataSource());
        sessionFactory.setPackagesToScan("com.gc.db.two");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }
	
	
	private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
  
        hibernateProperties.setProperty(
        		"hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");

        return hibernateProperties;
    }


}
