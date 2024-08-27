package com.service;

import com.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartServer {
    /**
     * 增加商品
     * @param cartItem
     * @return
     */
    int addCartItem(CartItem cartItem);

    /**
     * 得到商品的种类数
     * @param id
     * @return
     */
    int getCartItemCount(int id);

    /**
     * 得到所有购物车中的商品
     * @param memberId
     * @return
     */
    List<CartItem> listCartItem(int memberId);

    /**
     * 根据用户的id，得到购物车的总价格
     * @param member
     * @return
     */
    BigDecimal getTotalCart(int member);

    /**
     * 得到购物车的总数量
     * @return
     */
    int getCartItemCountNumber(int memberId);

    /**
     * 修改商品数量
     * @param memberId
     * @param furnId
     * @param count
     * @return
     */
    int updateCartItemCount(int memberId, int furnId, int count);

    /**
     * 通过memberId和furnId来查找CartItem
     * @param memberId
     * @param furnId
     * @return
     */
    CartItem getCartItemSingleByMemberIdAndFurnId(int memberId,int furnId);

    /**
     * 删除某一种类的商品
     * @param memberId
     * @param furnId
     * @return
     */
    int deleteCartItemByMemberIdAndFurnId(int memberId,int furnId);

    /**
     * 清空购物车
     * @param memberId
     * @return
     */
    public int clearCart(int memberId);
}
