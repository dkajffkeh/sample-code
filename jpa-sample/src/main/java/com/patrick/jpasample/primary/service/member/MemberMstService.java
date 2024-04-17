package com.patrick.jpasample.primary.service.member;

import com.patrick.jpasample.primary.entity.member.MemberMst;
import com.patrick.jpasample.primary.repository.MemberMstRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberMstService {

    private final MemberMstRepository memberMstRepository;

    public MemberMstService(
            MemberMstRepository memberMstRepository) {
        this.memberMstRepository = memberMstRepository;
    }

    @Transactional
    public void dirtyCheckingTest() {
        MemberMst memberMst = memberMstRepository.findById(1L);
        memberMst.updateUsername("유호연");
    }
}
