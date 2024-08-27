package com.test;

import com.entity.Member;
import com.service.impl.MemberServiceImpl;
import org.junit.Test;

public class MemberServiceImplTest {
    private MemberServiceImpl memberService=new MemberServiceImpl();
    @Test
    public void registerMemberTest(){
        if(memberService.registerMember(new Member(null,"admin","smith","smith@qq.com"))==1){
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }
    }
    @Test
    public void isExistsMemberTest(){
        if(memberService.isExistsMember("jacks")){
            System.out.println("存在该用户");
        }else{
            System.out.println("不存在该用户");
        }
    }
    @Test
    public void LoginMemberTest(){
        if(memberService.LoginMember(new Member(null,"adxmin","admin",null))!=null){
            System.out.println("登陆成功");
        }else{
            System.out.println("登录失败");
        }
    }
}
