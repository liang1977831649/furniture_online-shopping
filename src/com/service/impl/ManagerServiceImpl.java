package com.service.impl;

import com.dao.BasicDao;
import com.dao.ManagerDao;
import com.dao.impl.ManagerDaoImpl;
import com.entity.Manager;
import com.service.ManagerService;

public class ManagerServiceImpl extends BasicDao<Manager> implements ManagerService {
    private ManagerDao managerDao=new ManagerDaoImpl();
    @Override
    public Manager loginManager(Manager manager) {
        return managerDao.queryManagerByNameAndPassword(manager.getName(),manager.getPassword());
    }
}
