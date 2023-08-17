package com.patrick.jpasample.l1;

import com.patrick.jpasample.repository.MemberMstRepository;
import org.springframework.stereotype.Service;

@Service
public record L1CacheService(
        MemberMstRepository memberMstRepository) {

    public void innerSelect() {
        memberMstRepository.findById(1L);
    }
}
