package com.dao.impl;

import com.dao.BasicDao;
import com.dao.ManagerDao;
import com.entity.Manager;

public class ManagerDaoImpl extends BasicDao<Manager> implements ManagerDao {
    @Override
    public Manager queryManagerByNameAndPassword(String name,String password) {
        String sql="select * from manager where name=? and password=md5(?)";
       return super.QuerySingle(sql,Manager.class,name,password);
    }
}
