package com.patrick.jpasample.tertiary.repository;

import com.patrick.jpasample.tertiary.entity.MonitoringRuleGroupEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@EnableTransactionManagement
class MonitoringRuleGroupRepositoryTest {

    @Autowired
    private MonitoringRuleGroupRepository repository;

    @Test
    @Transactional
    void dirtyCheckingTest() {
        MonitoringRuleGroupEntity result = repository.findById(26L);
        result.modifyGroupName("hoho");
    }

}
