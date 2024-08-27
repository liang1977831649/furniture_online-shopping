package com.dao;

import com.entity.Manager;

public interface ManagerDao {
    public Manager queryManagerByNameAndPassword(String name,String password);
}
