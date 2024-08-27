package com.dao.impl;

import com.dao.BasicDao;
import com.dao.OrderDao;
import com.entity.Order;

import java.util.List;

public class OrderDaoImpl extends BasicDao<Order> implements OrderDao {
    @Override
    public int addOrder(Order order) {
        String sql="insert into `order` values(?,?,?,?,?)";
        return super.QueryCUD(sql,order.getId(),order.getDateTime(),order.getPrice(),order.getStatus(), order.getMemberId());
    }

    @Override
    public List<Order> queryOrderListByMemberId(int memberId) {
        String sql="select id,datetime,price,status,member_id as memberId from `order` where member_id=? ";
        return super.QueryMulti(sql,Order.class,memberId);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        String sql="select id,dateTime,price,status,member_id as memberId from `order` where id=?";
        return super.QuerySingle(sql,Order.class,orderId);
    }
}
