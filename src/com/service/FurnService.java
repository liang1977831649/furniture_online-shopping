package com.service;

import com.entity.Furn;
import com.entity.Page;

import java.util.List;

public interface FurnService {
    /**
     * 查找所有的商品
     * @return
     */
     List<Furn> queryFurns();

    /**
     * 添加商品
     * @param furn
     * @return
     */
     int addFurn(Furn furn);

    /**
     * 根据id删除家居
     * @param id
     * @return
     */
     int deleteFurnById(int id);

    /**
     * 根据id返回家居信息
     * @param id
     */
     Furn queryFurnById(int id);

    /**
     * 修改某个家居
     * @param furn
     * @return
     */
     int UpdateFurn(Furn furn);

    /**
     * 得到当前页数的家居信息，并初始化page
     * @return
     *
     * currentNum 表示当前页数是第几页
     * currentFurnNum 表示这一页一共有多少个家居
     */
     Page currentPageAndFurns(int currentNum,int currentFurnNum);

    /**
     * 返回商品的总数量
     * @return
     */
     int queryCountFurns();

    /**
     * 通过商品名称搜索商品，并返回page
     * @return
     */
    public Page<Furn> pageSearchFurnByName(String name,int pageCurrent,int pagePerFurnsNum);

}

