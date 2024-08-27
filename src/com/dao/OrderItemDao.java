package com.dao;

import com.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     *
     * @param orderItem
     */
    void addOrderItem(OrderItem orderItem);
    //void addOrderItem(QueryRunner queryRunner, Connection connection, int id, String name, int count, BigDecimal price, BigDecimal total, String orderId) throws SQLException;

    /**
     *得到订单项列表
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemsByOrderId(String orderId);

    int queryOrderItemsFurnCountNumber(String orderId);
}
