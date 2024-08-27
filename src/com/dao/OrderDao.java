package com.dao;

import com.entity.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 新增订单
     * @param id
     * @param dateTime
     * @param price 商品的总价格
     * @param status
     * @param memberId
     * @return
     */
    int addOrder(Order order);

    List<Order> queryOrderListByMemberId(int memberId);

    Order queryOrderByOrderId(String orderId);
}
