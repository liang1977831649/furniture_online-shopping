package com.dao.impl;

import com.entity.Member;
import com.dao.BasicDao;
import com.dao.MemberDao;

public class MemberDaoImpl extends BasicDao<Member> implements MemberDao {
    @Override
    public Member queryMemberByUsername(String username) {
        String sql="select * from member where username=?";
        return super.QuerySingle(sql, Member.class, username);
    }

    @Override
    public int saveMember(Member member) {
        String sql="insert into member(id,username,`password`,email) value(null,?,md5(?),?)";
        try {
            super.QueryCUD(sql,member.getUsername(),member.getPassword(),member.getEmail());
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Member queryMemberByUsernamePassword(String username,String password) {
        String sql="select * from member where username=? and password=md5(?)";
        return super.QuerySingle(sql,Member.class,username,password);
    }
}
