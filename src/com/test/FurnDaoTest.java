package com.test;

import com.dao.FurnDao;
import com.dao.impl.FurnDaoImpl;
import com.entity.Furn;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class FurnDaoTest {
    private FurnDao furnDao=new FurnDaoImpl();
    @Test
    public void queryFurns(){
        List<Furn> furns = furnDao.queryFurns();
        System.out.println(furns);
    }
    @Test
    public void addFurns(){
        int i = furnDao.InsertFurn("hello", "world", BigDecimal.valueOf(12), 20, 20, "path123");
        if(i==1){
            System.out.println("插入数据成功");
        }else{
            System.out.println("插入数据失败");
        }
    }
    @Test
    public void deleteFurn(){
        if(furnDao.deleteFurnById(17)>=1){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }
    @Test
    public void queryFurnByIdTest(){
        Furn furn = furnDao.queryFurnById(1);
        System.out.println(furn);
    }
    @Test
    public void updateFurnTest(){
        int i = furnDao.updateFurn(new Furn(21, "java", "web", new BigDecimal(100), 20, 10, null));
        if(i==1){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
    @Test
    public void queryCurrentPageFurnsTest(){
        List<Furn> furns = furnDao.queryCurrentPageFurns(2,5);
        System.out.println(furns);
    }
    @Test
    public void queryFurnsCountTest(){
        System.out.println( furnDao.queryFurnsCount());
    }
    @Test
    public void queryPageByNameTest(){
        List<Furn> furns = furnDao.queryFurnsByName("a", 1, 4);
        System.out.println(furns);
    }
    @Test
    public void countFurnBuNameTest(){
        int i = furnDao.queryFurnsCountByName("a");
        System.out.println(i);
    }
//    @Test
//    public void updateFurnSalesAndCountTest(){
//        System.out.println( furnDao.updateFurnSalesAndCount(1,8,10));
//    }
}
