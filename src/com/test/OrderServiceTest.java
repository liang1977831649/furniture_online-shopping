package com.test;

import com.entity.Order;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;
import com.utils.DataUtils;
import com.utils.DateTimeUtils;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {
    private OrderService orderService=new OrderServiceImpl();
    @Test
    public void createOrderTest(){
        Order order = new Order(DataUtils.createOrderId(), DateTimeUtils.getDateTimeNow(), new BigDecimal(250), "0", 22);
        int order1 = orderService.createOrder(order);
        System.out.println(order1);
    }
//    private String id;
//    private String dateTime;
//    private BigDecimal price;
//    private String status;
//    private Integer memberId;
    @Test
    public void CreateOrderTest(){
        orderService.createOrder(new Order(DataUtils.createOrderId(),DateTimeUtils.getDateTimeNow(),new BigDecimal( 853),"0",12));
    }
}
