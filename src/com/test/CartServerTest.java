package com.test;

import com.entity.CartItem;
import com.service.CartServer;
import com.service.impl.CartServerImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class CartServerTest {
    private CartServer cartServer = new CartServerImpl();

//    @Test
//    public void addCartItemTest() {
//        int i = cartServer.addCartItem(new CartItem(1, "舒适小椅", 1, new BigDecimal(12), new BigDecimal(24), 1,"assets"));
//        if (i == 1) {
//            System.out.println("已加入！");
//        } else {
//            System.out.println("未能加入！");
//        }
//    }
//
//    @Test
//    public void cartItemCountTest() {
//        System.out.println(cartServer.getCartItemCount(1));
//    }
//
//
    @Test
    public void CartItemList(){
        List<CartItem> cartItems = cartServer.listCartItem(22);
        System.out.println(cartItems);
    }
//    @Test
//    public void updateCartItemCountTest(){
//        //查找出这个商品
//        System.out.println( cartServer.updateCartItemCount(12,2,4));
//    }

    @Test
    public void deleteCartItemByMemberIdAndFurnIdTest(){
        System.out.println( cartServer.deleteCartItemByMemberIdAndFurnId(12,2));
    }

}
