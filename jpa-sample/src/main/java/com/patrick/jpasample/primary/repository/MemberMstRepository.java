package com.patrick.jpasample.primary.repository;

import com.patrick.jpasample.primary.entity.member.MemberMst;
import org.springframework.data.repository.Repository;

public interface MemberMstRepository extends Repository<MemberMst, Long> {

    MemberMst save(MemberMst memberMst);

    MemberMst findById(Long id);

}
