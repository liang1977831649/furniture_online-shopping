package com.service.impl;

import com.dao.impl.MemberDaoImpl;
import com.entity.Member;
import com.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private MemberDaoImpl memberDao=new MemberDaoImpl();
    @Override
    public int registerMember(Member member) {
        return memberDao.saveMember(member) ;
    }

    @Override
    public boolean isExistsMember(String name) {
        return memberDao.queryMemberByUsername(name) != null;
    }

    @Override
    public Member LoginMember(Member member) {
        return memberDao.queryMemberByUsernamePassword(member.getUsername(),member.getPassword());
    }
}
