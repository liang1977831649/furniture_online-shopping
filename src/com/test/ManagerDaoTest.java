package com.test;

import com.dao.ManagerDao;
import com.dao.impl.ManagerDaoImpl;
import org.junit.Test;

public class ManagerDaoTest {
    private ManagerDao managerDao=new ManagerDaoImpl();
    @Test
    public void queryManagerByNameAndPasswordTest(){
        if(managerDao.queryManagerByNameAndPassword("admin","admin")!=null){
            System.out.println("账号密码正确");
        }else{
            System.out.println("账号密码错误");
        }
    }
}
