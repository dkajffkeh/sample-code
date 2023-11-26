package com.patrick.jpasample.repository;

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
        memberMst.updateMemberName("유호연");
    }

}
