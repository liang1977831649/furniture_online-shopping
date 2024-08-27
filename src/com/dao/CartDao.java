package com.dao;

import com.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartDao {
    /**
     * 添加商品
     * @param cartItem
     */
    int addCartItem(CartItem cartItem);

    /**
     * 删除商品
     * @param id
     */
    int deleteCartItemById(Integer id );

    /**
     * 展示商品
     * @return
     */
    List<CartItem> getCartItems(int memberId);

    /**
     * 更新数量
     * @param furnId
     * @param count
     * @return
     */
    int updateCount(int furnId, int memberId,int count,BigDecimal total);

    /**
     * 得到购物车商品的种类总数量
     * @param id
     * @return
     */
    int getTotalFurnCount(int id);

    /**
     * 根据id，得到总价格
     * @return
     */
    BigDecimal getTotalPrice(int id );

    /**
     * 根据furnId,memberId来查询有没有这个CartItem
     * @param furnId
     * @param memberId
     * @return
     */
    CartItem queryCartItemByMemberIdAndFurnId(int furnId,int memberId);

    /**
     * 得到购物车商品的总数量
     * @param memberId
     * @return
     */
    int getCartItemCountNumber(int memberId);

    /**
     * 删除某种商品
     * @param memberId
     * @param furnId
     * @return
     */
    int deleteCartItem(int memberId,int furnId);

    /**
     * 清空购物车
     * @param memberId
     * @return
     */
    int deleteAllCartItemByMemberId(int memberId);
}
