package com.test;

import com.dao.OrderDao;
import com.dao.impl.OrderDaoImpl;
import com.entity.Order;
import com.utils.DataUtils;
import com.utils.DateTimeUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderDaoTest {
    OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void addOrder(){
        int i = orderDao.addOrder(new Order( DataUtils.createOrderId(), DateTimeUtils.getDateTimeNow(), new BigDecimal(2000),"0", 1));
        System.out.println(i);
    }
    @Test
    public void queryOrderByMemberId(){
        List<Order> orders = orderDao.queryOrderListByMemberId(12);
        System.out.println(orders);
    }
    @Test
    public void queryOrderByOrderIdTest(){
        Order order = orderDao.queryOrderByOrderId("202403311715352505");
        System.out.println(order);
    }
}
