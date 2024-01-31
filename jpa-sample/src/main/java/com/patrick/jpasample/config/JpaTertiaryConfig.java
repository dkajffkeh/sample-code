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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.patrick.jpasample.tertiary"},
        entityManagerFactoryRef = "tertiaryEntityManagerFactory",
        transactionManagerRef = "tertiaryTransactionManager"
)
public class JpaTertiaryConfig {

    @Value("${spring.datasource-tertiary.hibernate.dialect}")
    private String dialect;

    @Value("${spring.datasource-tertiary.hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean
    @ConfigurationProperties("spring.datasource-tertiary")
    public DataSourceProperties tertiaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource tertiaryDataSource(
            @Qualifier("tertiaryDataSourceProperties") DataSourceProperties tertiaryDataSourceProperties) {
        return tertiaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean tertiaryEntityManagerFactory(
            EntityManagerFactoryBuilder tertiaryEntityManagerFactoryBuilder,
            @Qualifier("tertiaryDataSource") DataSource tertiaryDataSource) {

        Map<String, String> tertiaryJpaProperties = new HashMap<>();
        tertiaryJpaProperties.put("hibernate.dialect", dialect);
        tertiaryJpaProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        tertiaryJpaProperties.put("hibernate.physical_naming_strategy", JpaNamingStrategy.class.getName());

        return tertiaryEntityManagerFactoryBuilder
                .dataSource(tertiaryDataSource)
                .packages("com.patrick.jpasample.tertiary")
                .persistenceUnit("tertiaryEntityManager")
                .properties(tertiaryJpaProperties)
                .build();
    }

    @Bean
    public PlatformTransactionManager tertiaryTransactionManager(
            @Qualifier("tertiaryEntityManagerFactory") EntityManagerFactory tertiaryEntityManagerFactory) {
        return new JpaTransactionManager(tertiaryEntityManagerFactory);
    }
}
