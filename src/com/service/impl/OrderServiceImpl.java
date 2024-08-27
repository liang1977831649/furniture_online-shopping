package com.service.impl;

import com.dao.CartDao;
import com.dao.FurnDao;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.dao.impl.CartDaoImpl;
import com.dao.impl.FurnDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.dao.impl.OrderItemDaoImpl;
import com.entity.CartItem;
import com.entity.Furn;
import com.entity.Order;
import com.entity.OrderItem;
import com.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private FurnDao furnDao=new FurnDaoImpl();
    private CartDao cartDao =new CartDaoImpl();
    @Override
    public int createOrder(Order order) {
        orderDao.addOrder(order);
        List<CartItem> cartItems = cartDao.getCartItems(order.getMemberId());
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem(cartItem.getId(), cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotal(), order.getId());
            orderItemDao.addOrderItem(orderItem);//保存到db中Order Item表的数据
            int i=1/0;
            Furn furn = furnDao.queryFurnById(cartItem.getId());
            furn.setSales(furn.getSales()+cartItem.getCount());
            furn.setStock(furn.getStock()-cartItem.getCount());
            furnDao.updateFurn(furn);//修改furn的表
        }
        cartDao.deleteAllCartItemByMemberId(order.getMemberId());
        //返回生成订单项的商品总数量
        return 1;
    }

    @Override
    public List<Order> queryOrderByMemberId(int member) {
        return orderDao.queryOrderListByMemberId(member);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        return orderDao.queryOrderByOrderId(orderId);
    }
}
