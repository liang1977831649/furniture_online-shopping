package com.service;

import com.entity.CartItem;
import com.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    /**
     * 增加订单项
     * @param cartItems
     * @param orderId
     */
    void addOrderItem(List<CartItem> cartItems, String orderId);
    //void addOrderItem(Connection connection, QueryRunner queryRunner, List<CartItem> cartItems, String orderId) throws SQLException;

    /**
     * 根据订单编号，查到订单项
     * @param orderId
     * @return
     */
    List<OrderItem> queryOrderItemsByOrderId(String orderId);

    /**
     * 根据OrderId，得到商品的总数量
     * @param orderId
     */
    public int queryOrderItemFurnsCountByOrderId(String orderId);
}
