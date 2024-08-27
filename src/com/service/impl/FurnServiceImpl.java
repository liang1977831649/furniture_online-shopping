package com.service.impl;

import com.dao.FurnDao;
import com.dao.impl.FurnDaoImpl;
import com.entity.Furn;
import com.entity.Page;
import com.service.FurnService;

import java.util.List;

public class FurnServiceImpl implements FurnService {
    private FurnDao furnDao = new FurnDaoImpl();

    public List<Furn> queryFurns() {
        return furnDao.queryFurns();
    }

    @Override
    public int addFurn(Furn furn) {
        return furnDao.InsertFurn(furn.getName(), furn.getBusiness(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());
    }

    @Override
    public int deleteFurnById(int id) {
        return furnDao.deleteFurnById(id);
    }

    @Override
    public Furn queryFurnById(int id) {
        return furnDao.queryFurnById(id);
    }

    public int UpdateFurn(Furn furn) {
        return furnDao.updateFurn(furn);
    }

    @Override
    public Page<Furn> currentPageAndFurns(int currentPageNum, int currentFurnsNum) {

        //得到家居的总数
        int furnsCount = furnDao.queryFurnsCount();
        //得到当前页数的家居信息
        List<Furn> furns = furnDao.queryCurrentPageFurns(currentPageNum, currentFurnsNum);
        //根据每页显示的家居数量，得到最多能够有多少页数
        int pageAllCount = furnsCount / currentFurnsNum;
        if (furnsCount % currentFurnsNum != 0) {
            //磨得到的结果加1
            pageAllCount++;
        }
        Page<Furn> page = new Page(pageAllCount, currentPageNum, furnsCount, currentFurnsNum, furns);
        return page;
    }

    @Override
    public Page<Furn> pageSearchFurnByName(String name, int pageCurrent, int pagePerFurnsNum) {
        //得到商品总数量
        int countFurns = furnDao.queryFurnsCountByName(name);
        //得到商品的总页数
        int pageNum = (int) Math.ceil(1.0 * countFurns / pagePerFurnsNum);
        //得到搜索的结果
        List<Furn> furns = furnDao.queryFurnsByName(name, pageCurrent, pagePerFurnsNum);
        //初始化page
        Page<Furn> page = new Page<>(pageNum, pageCurrent, countFurns, pagePerFurnsNum, furns);
        return page;
    }



    @Override
    public int queryCountFurns() {
        return furnDao.queryFurnsCount();
    }
}
