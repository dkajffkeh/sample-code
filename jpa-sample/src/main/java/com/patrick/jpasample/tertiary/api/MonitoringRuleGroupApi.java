package com.patrick.jpasample.tertiary.api;

import com.patrick.jpasample.tertiary.service.MonitoringRuleGroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringRuleGroupApi {

    private final MonitoringRuleGroupService service;

    public MonitoringRuleGroupApi(
            MonitoringRuleGroupService service) {
        this.service = service;
    }

    @GetMapping("/group/dirty")
    public void isChecked() {
        service.dirtyChecked();
    }

}
