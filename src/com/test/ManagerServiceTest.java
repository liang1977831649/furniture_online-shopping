package com.test;

import com.entity.Manager;
import com.service.ManagerService;
import com.service.impl.ManagerServiceImpl;
import org.junit.Test;

public class ManagerServiceTest {
    private ManagerService managerService=new ManagerServiceImpl();
    @Test
    public void queryManagerByNameAndPassword(){
        if(managerService.loginManager(new Manager(null,"admin","admin"))!=null){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }
}
