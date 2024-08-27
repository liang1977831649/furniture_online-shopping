package com.service.impl;

import com.dao.OrderItemDao;
import com.dao.impl.OrderItemDaoImpl;
import com.entity.CartItem;
import com.entity.OrderItem;
import com.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
//    @Override
//    public void addOrderItem(Connection connection, QueryRunner queryRunner, List<CartItem> cartItems, String orderId) throws SQLException {
//        for (CartItem cartItem : cartItems) {
//            orderItemDao.addOrderItem(queryRunner,connection, cartItem.getId(),cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotal(),orderId);
//        }
//    }

    @Override
    public void addOrderItem(List<CartItem> cartItems, String orderId) {
        for (CartItem cartItem : cartItems) {
            orderItemDao.addOrderItem(new OrderItem( cartItem.getId(),cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotal(),orderId));
        }
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public int queryOrderItemFurnsCountByOrderId(String orderId) {
        return orderItemDao.queryOrderItemsFurnCountNumber(orderId);
    }
}
