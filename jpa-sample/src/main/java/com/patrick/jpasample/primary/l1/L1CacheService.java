package com.patrick.jpasample.primary.l1;

import com.patrick.jpasample.primary.repository.MemberMstRepository;
import org.springframework.stereotype.Service;

@Service
public record L1CacheService(
        MemberMstRepository memberMstRepository) {

    public void innerSelect() {
        memberMstRepository.findById(1L);
    }
}
