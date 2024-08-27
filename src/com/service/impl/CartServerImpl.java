package com.service.impl;

import com.dao.CartDao;
import com.dao.impl.CartDaoImpl;
import com.entity.CartItem;
import com.service.CartServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CartServerImpl implements CartServer {
    private CartDao cartDao=new CartDaoImpl();
    public int addCartItem(CartItem cartItem){
        //（1）这里需要做一个调整，如果添加的商品在自己的购物车里已有，那么只需要修改数量就行
        //（2）如果不是，那就进行添加操作
        //先查询这个是否有这个商品
        CartItem cartTransient = cartDao.queryCartItemByMemberIdAndFurnId(cartItem.getId(), cartItem.getMemberId());
        if(cartTransient!=null){
            //只需要修改数量和价格
            int total=(cartTransient.getCount()+1)*cartTransient.getPrice().intValue();
            return cartDao.updateCount(cartTransient.getId(),cartTransient.getMemberId(),cartTransient.getCount()+1,new BigDecimal( total));
        }else{
            //新增
            return cartDao.addCartItem(cartItem);
        }
    }
    public int getCartItemCount(int id){
        return ((Number)cartDao.getTotalFurnCount(id)).intValue();
    }

    public List<CartItem> listCartItem(int memberId){
        //获取list
        List<CartItem> cartItems = cartDao.getCartItems(memberId);
        return cartItems;
    }

    @Override
    public BigDecimal getTotalCart(int member) {
        return cartDao.getTotalPrice(member);
    }

    @Override
    public int getCartItemCountNumber(int memberId) {
        return cartDao.getCartItemCountNumber(memberId);
    }

    @Override
    public int updateCartItemCount(int memberId, int furnId, int count) {
        //先查找出这个商品，然后单价和数量相乘，得到总价
        CartItem cartItem = cartDao.queryCartItemByMemberIdAndFurnId(furnId, memberId);
        //判空处理
        BigDecimal total=new BigDecimal(0);
        if(cartItem!=null){
            total=new BigDecimal (cartItem.getPrice().intValue()*count);
        }
        return cartDao.updateCount(furnId,memberId,count,total);
    }

    @Override
    public CartItem getCartItemSingleByMemberIdAndFurnId(int memberId, int furnId) {
        return cartDao.queryCartItemByMemberIdAndFurnId(furnId,memberId);
    }

    @Override
    public int deleteCartItemByMemberIdAndFurnId(int memberId, int furnId) {
        return cartDao.deleteCartItem(memberId,furnId);
    }

    @Override
    public int clearCart(int memberId) {
        return cartDao.deleteAllCartItemByMemberId(memberId);
    }

}
