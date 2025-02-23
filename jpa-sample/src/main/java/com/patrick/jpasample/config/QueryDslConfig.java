package com.patrick.jpasample.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager primaryEntityManager;

    @PersistenceContext(unitName = "secondaryEntityManager")
    private EntityManager secondaryEntityManager;

    @Bean(name = "primaryFactory")
    public JPAQueryFactory primaryFactory() {
        return new JPAQueryFactory(primaryEntityManager);
    }

    @Bean(name = "secondaryFactory")
    public JPAQueryFactory secondaryFactory() {
        return new JPAQueryFactory(secondaryEntityManager);
    }


}
