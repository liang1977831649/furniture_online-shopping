package com.dao;

import com.entity.Member;

public interface MemberDao {
    /**
     * 根据用户名返回一个Member对象
     * @param username 用户名
     * @return
     */
    public Member queryMemberByUsername(String username);

    /**
     * 根据member将用户信息保存在数据库中，新建
     * @param member
     * @return
     */
    public int saveMember(Member member);

    /**
     * 根据根据用户名和密码来查询并返回用户
     * @return
     */
    public Member queryMemberByUsernamePassword(String username,String password);
}
