package com.patrick.jpasample.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.patrick.jpasample.secondary"},
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager"
)
public class JpaSecondaryConfig {

    @Value("${spring.datasource-secondary.hibernate.dialect}")
    private String dialect;

    @Value("${spring.datasource-secondary.hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource-secondary")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource secondaryDataSource(
            @Qualifier("secondaryDataSourceProperties") DataSourceProperties secondaryDataSourceProperties) {
        return secondaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            EntityManagerFactoryBuilder secondaryEntityManagerFactoryBuilder,
            @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {

        Map<String, String> secondaryJpaProperties = new HashMap<>();
        secondaryJpaProperties.put("hibernate.dialect", dialect);
        secondaryJpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        secondaryJpaProperties.put("hibernate.physical_naming_strategy", JpaNamingStrategy.class.getName());

        return secondaryEntityManagerFactoryBuilder
                .dataSource(secondaryDataSource)
                .packages("com.patrick.jpasample.secondary")
                .persistenceUnit("secondaryEntityManager")
                .properties(secondaryJpaProperties)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}
