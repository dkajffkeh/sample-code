package com.patrick.jpasample.primary.api;

import com.patrick.jpasample.primary.api.param.CreateMemberRequestParam;
import com.patrick.jpasample.primary.l1.L1CacheService;
import com.patrick.jpasample.primary.repository.MemberMstRepository;
import com.patrick.jpasample.primary.service.member.MemberMstService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
public class MemberMstController {

    private final MemberMstRepository memberMstRepository;
    private final L1CacheService l1CacheService;
    private final MemberMstService memberMstService;

    public MemberMstController(
            MemberMstRepository memberMstRepository,
            L1CacheService l1CacheService,
            MemberMstService memberMstService) {
        this.memberMstRepository = memberMstRepository;
        this.l1CacheService = l1CacheService;
        this.memberMstService = memberMstService;
    }


    @PostMapping
    public void createMember(@RequestBody CreateMemberRequestParam createMemberRequestParam) {
        memberMstRepository.save(createMemberRequestParam.toEntity());
    }

    @GetMapping("/l1")
    @Transactional
    public void l1CacheTest() {
        l1CacheService.innerSelect();
        memberMstRepository.findById(1L);
    }

    @GetMapping("/l2")
    public void l2CacheTest() {
        l1CacheService.innerSelect();
    }

    @GetMapping("/test/dirty-checking")
    public void dirtyCheckingTest() {
        memberMstService.dirtyCheckingTest();
    }
}
