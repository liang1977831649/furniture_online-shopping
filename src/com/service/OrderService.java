package com.service;

import com.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * 增加订单
     * @param order
     * @return
     */
    int createOrder(Order order);

    /**
     * 查找订单
     * @param member
     * @return
     */
    List<Order> queryOrderByMemberId(int member);

    Order queryOrderByOrderId(String orderId);
}
