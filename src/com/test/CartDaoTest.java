package com.test;

import com.dao.CartDao;
import com.dao.impl.CartDaoImpl;
import com.entity.Cart;
import com.entity.CartItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class CartDaoTest {
    private CartDao cartDao=new CartDaoImpl();
    /*
        private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal total;
    private String username;
     */
//    @Test
//    public void addCartItemTest(){
//        System.out.println( cartDao.addCartItem(new CartItem(23, "3124", 2, new BigDecimal( 231), new BigDecimal(462), 12,"assets")));
//    }
//    @Test
//    public void deleteCartItemByIdTest(){
//        System.out.println(cartDao.deleteCartItemById(1));
//    }
//    @Test
//    public void getCartItems(){
//        List<CartItem> cartItems = cartDao.getCartItems(1);
//        System.out.println(cartItems);
//    }
//    @Test
//    public void updateCountTest(){
//        System.out.println( cartDao.updateCount(1,1,1,new BigDecimal(60)));
//    }
//    @Test
//    public void getTotalCountTest(){
//        System.out.println( cartDao.getTotalFurnCount(1));
//    }
    @Test
    public void getTotal(){
        BigDecimal totalPrice = cartDao.getTotalPrice(22);
        System.out.println(totalPrice);
    }
//    @Test
//    public void queryCartItemByMemberIdAndFurnIdTest() {
//        CartItem cartItem = cartDao.queryCartItemByMemberIdAndFurnId(1, 1);
//        System.out.println(cartItem);
//    }
    @Test
    public void getCartItemCountNumberTest(){
        Integer cartItemCountNumber=cartDao.getCartItemCountNumber(12);
        System.out.println(cartItemCountNumber);
    }
    @Test
    public void deleteCartItemTest(){
        System.out.println( cartDao.deleteCartItem(1, 40));
    }

    @Test
    public void deleteAllCartItemByMemberIdTest(){
        int i = cartDao.deleteAllCartItemByMemberId(22);
        System.out.println(i);
    }
}
