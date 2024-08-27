package com.test;

import com.entity.Member;
import com.dao.impl.MemberDaoImpl;
import org.junit.Test;

public class MemberDaoImplTest {
    private MemberDaoImpl memberDao=new MemberDaoImpl();
    @Test
    public void queryMemberByUsernameTest(){
        String name="admin";
        if(memberDao.queryMemberByUsername(name)!=null){
            System.out.println("存在这个对象");
        }else{
            System.out.println("不存在这个对象");
        }
    }
    @Test
    public void saveMemberTest(){
        Member member = new Member(null, "jack", "jack", "jack@qq.com");
        if(memberDao.saveMember(member)>=1){
            System.out.println("保存成功！");
        }else{
            System.out.println("保存失败！");
        }
    }
    @Test
    public void queryMemberByUsernamePasswordTest(){
        if(memberDao.queryMemberByUsernamePassword("admin", "admixn")!=null){
            System.out.println("存在该用户，登陆成功");
        }else{
            System.out.println("用户密码错误");
        }
    }
}
