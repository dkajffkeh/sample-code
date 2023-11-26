package com.patrick.jpasample.primary.api.param;

import com.patrick.jpasample.primary.entity.member.MemberMst;

public class CreateMemberRequestParam {

    private String memberName;

    public CreateMemberRequestParam() {

    }

    public CreateMemberRequestParam(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public MemberMst toEntity() {
        return new MemberMst(this.memberName);
    }
}
