package com.dao.impl;

import com.dao.BasicDao;
import com.dao.CartDao;
import com.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class CartDaoImpl extends BasicDao<CartItem> implements CartDao {
    @Override
    public int addCartItem(CartItem cartItem) {
        String sql="insert  into CartItem(id, name,count,price,total,memberId,img_path) values(?,?,?,?,?,?,?)";
        return super.QueryCUD(sql,cartItem.getId(), cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotal(),cartItem.getMemberId(),cartItem.getImg());
    }

    @Override
    public int deleteCartItemById(Integer id) {
        String sql="delete from CartItem where id=?";
        return super.QueryCUD(sql,id);
    }

    @Override
    public List<CartItem> getCartItems(int memberId) {
        String sql="select id,name,count,price,total,memberId,img_path as img from CartItem where memberId=?";
        return  super.QueryMulti(sql, CartItem.class,memberId);
    }

    @Override
    public int updateCount(int furnId, int memberId,int count,BigDecimal total) {
        String sql="update cartItem set count=? , total=? where memberId=? and id=?";
        return super.QueryCUD(sql,count,total,memberId,furnId);
    }

    @Override
    public int getTotalFurnCount(int id) {
       String sql="select COUNT(*) from CartItem where memberId=?";
       return ((Number)super.QueryOne(sql,id)).intValue();
    }

    @Override
    public BigDecimal getTotalPrice(int id) {
        String sql="select SUM(total) from CartItem where memberId=?";
        Object o = super.QueryOne(sql, id);
        if(o==null){
            return new BigDecimal(0);
        }
        return (BigDecimal)o;
    }
    public CartItem queryCartItemByMemberIdAndFurnId(int furnId,int memberId){
        String sql="select  id,name,count,price,total,memberId,img_path from CartItem where id=? and MemberId=?";
        return super.QuerySingle(sql, CartItem.class, furnId, memberId);
    }

    @Override
    public int getCartItemCountNumber(int memberId) {
        String sql="select sum(count) from CartItem where memberid=? ";
        Object number = super.QueryOne(sql, memberId);
        if(number==null){
            return 0;
        }
        return ((Number)(number)).intValue();
    }

    @Override
    public int deleteCartItem(int memberId, int furnId) {
        String sql="delete from cartItem where memberId=? and id=?";
         return super.QueryCUD(sql, memberId, furnId);
    }

    @Override
    public int deleteAllCartItemByMemberId(int memberId) {
        String sql="delete from cartItem where memberId=?";
        return super.QueryCUD(sql,memberId);
    }
}
