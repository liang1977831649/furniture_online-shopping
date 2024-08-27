package com.service;

import com.entity.Member;

public interface MemberService {
    /**
     * 注册用户
     * @param member
     * @return
     */
    int registerMember(Member member);

    /**
     * 判断用户存不存在
     * @param name
     * @return
     */
    boolean isExistsMember(String name);

    /**
     * 根据用户的用户名和密码来验证登录
     * @param name
     * @param password
     * @return
     */
    public Member LoginMember(Member member);
}
