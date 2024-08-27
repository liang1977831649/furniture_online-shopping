package com.web;

import com.entity.CartItem;
import com.entity.Order;
import com.service.CartServer;
import com.service.FurnService;
import com.service.OrderItemService;
import com.service.OrderService;
import com.service.impl.CartServerImpl;
import com.service.impl.FurnServiceImpl;
import com.service.impl.OrderItemServiceImpl;
import com.service.impl.OrderServiceImpl;
import com.utils.DataUtils;
import com.utils.DateTimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends BasicServlet {
    private OrderService orderService = new OrderServiceImpl();
    private OrderItemService orderItemService = new OrderItemServiceImpl();
    private CartServer cartServer = new CartServerImpl();
    private FurnService furnService = new FurnServiceImpl();

    public void addOrderAndOrderItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        System.out.println("addOrderAndOrderItem被调用。。。");
        //前期准备工作
        int memberId = Integer.parseInt(request.getParameter("memberId"));//用户id

        List<CartItem> cartItems = cartServer.listCartItem(memberId);//得到这个用户的商品列表
        BigDecimal totalCart = cartServer.getTotalCart(memberId);//得到这个用户买这些商品的总金额
        String orderId = DataUtils.createOrderId();//得到这一份订单的Id
        String dateTimeNow = DateTimeUtils.getDateTimeNow();//得到下订单的时间

        //检查库存是否充足
        String satisfyRequire = stockSatisfyRequire(cartItems);
        if (satisfyRequire != null) {
            request.setAttribute("errorMsg","商品【"+satisfyRequire+"】库存量不足");
            //动作，说明失败了，库存量不足
            request.getRequestDispatcher("/CartServlet?action=cartItemManagement").forward(request,response);
        }

        //设置取消自动提交事务
        //先生成一份订单
        Order order = new Order(orderId, dateTimeNow, totalCart, "0", memberId);
        orderService.createOrder(order);//生成的订单存到数据库中
//        orderItemService.addOrderItem(cartItems, orderId);//生成订单项存到数据库中
//        furnService.updateFurnSalesAndCount(cartItems);//更新数据库中的Furn表的销售量和库存量数据
//        cartServer.clearCart(memberId);//清空购物车
        List<Order> orders = orderService.queryOrderByMemberId(memberId);
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/views/order/order.jsp").forward(request,response);

    }
    public void listOrderItems(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        int memberId =Integer.parseInt( request.getParameter("memberId"));
        //拿到对应的账单编号
        String orderId = request.getParameter("orderId");
        //拿到对应的订单
        request.setAttribute("order",orderService.queryOrderByOrderId(orderId));
        request.setAttribute("count",orderItemService.queryOrderItemFurnsCountByOrderId(orderId));
        request.setAttribute("orderItems",orderItemService.queryOrderItemsByOrderId(orderId));
        request.getRequestDispatcher("/views/order/order_detail.jsp").forward(request,response);
    }

    public void orderManager(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("orderManager被调用");
        int memberId =Integer.parseInt( request.getParameter("memberId"));
        List<Order> orders = orderService.queryOrderByMemberId(memberId);
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/views/order/order.jsp").forward(request,response);
    }

    public String stockSatisfyRequire(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            //库存量不足
            if (furnService.queryFurnById(cartItem.getId()).getStock() - cartItem.getCount() < 0) {
                return cartItem.getName();
            }
        }
        return null;
    }

}