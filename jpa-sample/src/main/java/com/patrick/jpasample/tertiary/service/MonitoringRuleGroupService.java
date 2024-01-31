package com.patrick.jpasample.tertiary.service;

import com.patrick.jpasample.tertiary.entity.MonitoringRuleGroupEntity;
import com.patrick.jpasample.tertiary.repository.MonitoringRuleGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MonitoringRuleGroupService {

    private final MonitoringRuleGroupRepository repository;

    public MonitoringRuleGroupService(
            MonitoringRuleGroupRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void dirtyChecked() {
        MonitoringRuleGroupEntity result = repository.findById(26L);
        result.modifyGroupName("hoho");
    }
}
