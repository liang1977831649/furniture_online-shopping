package com.dao;

import com.entity.Furn;

import java.math.BigDecimal;
import java.util.List;

public interface FurnDao {
    List<Furn> queryFurns();

    int InsertFurn(String name, String business, BigDecimal price, Integer sales, Integer stock, String imgPath);

    int deleteFurnById(int id);

    Furn queryFurnById(int id);

    int updateFurn(Furn furn);

    List<Furn> queryCurrentPageFurns(int pageNumber, int currentFurnsNum);

    int queryFurnsCount();

    int queryFurnsCountByName(String name);

    List<Furn> queryFurnsByName(String name, int start, int currentFurnsNum);


}

