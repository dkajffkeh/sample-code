package com.patrick.jpasample.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.patrick.jpasample.primary.entity.member.MemberMst;
import com.patrick.jpasample.primary.repository.MemberMstRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@EnableTransactionManagement
class ProductRepositoryTest {

    @Autowired
    private MemberMstRepository memberMstRepository;

    @Test
    @Transactional
    void changeNameTest() {
        MemberMst memberMst = memberMstRepository.findById(1L);
        memberMst.updateUsername("유호연");
        MemberMst updatedMember = memberMstRepository.findById(1L);
        assertEquals("유호연", updatedMember.getMemberName());
    }

}
