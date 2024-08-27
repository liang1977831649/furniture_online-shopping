package com.dao.impl;

import com.dao.BasicDao;
import com.dao.FurnDao;
import com.entity.Furn;

import java.math.BigDecimal;
import java.util.List;

public class FurnDaoImpl extends BasicDao<Furn> implements FurnDao {
    @Override
    public  List<Furn> queryFurns() {
        String sql="select id,name,business,price,sales,stock,img_path as imgpath from furn";
        return super.QueryMulti(sql, Furn.class);
    }

    @Override
    public int InsertFurn(String name, String business, BigDecimal price, Integer sales, Integer stock, String imgPath ) {
        String sql="insert into furn value(null,?,?,?,?,?,?)";
        return super.QueryCUD(sql, name, business, price, sales, stock, imgPath);
    }

    @Override
    public int deleteFurnById(int id) {
        String sql="delete  from furn where id=?";
        return super.QueryCUD(sql,id);
    }

    @Override
    public Furn queryFurnById(int id) {
        String sql="select id,name,business,price,sales,stock,img_path as imgPath from furn where id=?";
        Furn furn = super.QuerySingle(sql, Furn.class, id);
        return furn;
    }

    @Override
    public int updateFurn(Furn furn) {
        //assets/images/product-image/default.jpg
        String sql="update furn set name=?,business=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return super.QueryCUD(sql,furn.getName(),furn.getBusiness(),furn.getPrice(),furn.getSales(),furn.getStock(),furn.getImgPath(),furn.getId());
    }

    @Override
    public List<Furn> queryCurrentPageFurns(int pageNumber,int currentFurnsNum) {
        String sql="select id,name,business,price,sales,stock,img_path as imgpath from furn limit ?,?";
        //先得到
        List<Furn> furns = super.QueryMulti(sql, Furn.class, (pageNumber-1)*currentFurnsNum,currentFurnsNum);
        return furns;
    }

    @Override
    public int queryFurnsCount() {
        String sql="select count(*) from furn";
        return Integer.parseInt(String.valueOf(super.QueryOne(sql)));
    }

    @Override
    public int queryFurnsCountByName(String name) {
        String sql="select count(*) from furn where name like '%"+name+"%'";
        return ((Number)super.QueryOne(sql)).intValue();
    }

    @Override
    public List<Furn> queryFurnsByName(String name,int startPage,int currentFurnsNum) {
        String sql="select id,name,business,price,sales,stock,img_path as imgpath from furn where name like '%"+name+"%' limit ?,?";
        List<Furn> furns = super.QueryMulti(sql, Furn.class, (startPage-1)*currentFurnsNum,currentFurnsNum);
        return furns;
    }

}
