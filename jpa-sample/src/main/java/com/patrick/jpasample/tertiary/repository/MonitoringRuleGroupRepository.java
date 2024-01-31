package com.patrick.jpasample.tertiary.repository;

import com.patrick.jpasample.tertiary.entity.MonitoringRuleGroupEntity;
import org.springframework.data.repository.Repository;

public interface MonitoringRuleGroupRepository extends Repository<MonitoringRuleGroupEntity, Long> {

    MonitoringRuleGroupEntity findById(Long id);

}
