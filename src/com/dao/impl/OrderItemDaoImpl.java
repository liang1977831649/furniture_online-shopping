package com.dao.impl;

import com.dao.BasicDao;
import com.dao.OrderItemDao;
import com.entity.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BasicDao<OrderItem> implements OrderItemDao {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql="insert into `orderItem` values(?,?,?,?,?,?)";
        super.QueryCUD(sql,orderItem.getId(),orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql="select id,name,count,price,total_price as totalPrice,order_id as orderId from orderitem where order_id=?";
        return super.QueryMulti(sql,OrderItem.class,orderId);
    }

    @Override
    public int queryOrderItemsFurnCountNumber(String orderId) {
        String sql="select sum(count) from orderItem where order_id=?";
        return ((Number)super.QueryOne(sql,orderId)).intValue();
    }

//    @Override
//    public void addOrderItem(QueryRunner queryRunner, Connection connection, int id, String name, int count, BigDecimal price, BigDecimal total, String orderId) throws SQLException {
//        String sql="insert into `orderItem` values(?,?,?,?,?,?)";
//        queryRunner.update(connection, sql, id, name, count, price, total, orderId);
//    }
}
