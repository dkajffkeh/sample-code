package com.patrick.jpasample.tertiary.entity;

import java.security.SecureRandom;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_cmc_monitoring_rule_group")
public class MonitoringRuleGroupEntity {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 33;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", length = 30, nullable = false)
    private String groupName;

    @Column(name = "group_key", length = 100, nullable = false)
    private String groupKey;

    public MonitoringRuleGroupEntity() {

    }

    public MonitoringRuleGroupEntity(String groupName, String groupKey) {
        this.id = null;
        this.groupName = groupName;
        this.groupKey = groupKey;
    }

    @Transient
    public static String keyGen() {
        StringBuilder stringBuilder = new StringBuilder(LENGTH);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public void modifyGroupName(String groupName) {
        this.groupName = groupName;
    }
}
