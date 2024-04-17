package com.patrick.jpasample.primary.entity.member;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Table(name = "MEMBER_MST")
@Cacheable
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    public MemberMst() {

    }

    public MemberMst(String memberName) {
        this(null, memberName);
    }

    public MemberMst(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public String getMemberName() {
        return this.username;
    }
}
