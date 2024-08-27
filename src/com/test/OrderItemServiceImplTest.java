package com.test;

import com.dao.CartDao;
import com.dao.impl.CartDaoImpl;
import com.service.OrderItemService;
import com.service.impl.OrderItemServiceImpl;

public class OrderItemServiceImplTest {
    private OrderItemService orderItemService=new OrderItemServiceImpl();
    private CartDao cartDao=new CartDaoImpl();
//    @Test
//    public void addOrderItemTest(){
//        List<CartItem> cartItems = cartDao.getCartItems(22);
//        Connection connection = JDBCUtilsDruid.getConnect();
//        QueryRunner queryRunner = new QueryRunner();
//        try {
//            connection.setAutoCommit(false);
////            orderItemService.addOrderItem(connection,queryRunner, cartItems,"202403311232162451");
////            int i=1/0;
//            connection.commit();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            JDBCUtilsDruid.closeConnect(null,connection,null);
//        }
//    }

}
