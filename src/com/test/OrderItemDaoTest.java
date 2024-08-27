package com.test;

import com.dao.OrderItemDao;
import com.dao.impl.OrderItemDaoImpl;
import com.entity.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemDaoTest {
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    @Test
    public void addOrderItem(){
        orderItemDao.addOrderItem(new OrderItem(23,"3142",3,new BigDecimal(231),new BigDecimal(693),"202403301646165013"));
    }
    @Test
    public void queryOrderItemsByOrderId(){
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId("202403311715352505");
        System.out.println(orderItems);
    }
    @Test
    public void queryOrderItemsFurnCountNumberTest(){
        int i = orderItemDao.queryOrderItemsFurnCountNumber("202403311715352505");
        System.out.println(i);
    }
}
