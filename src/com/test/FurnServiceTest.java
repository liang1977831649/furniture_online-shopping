package com.test;

import com.entity.Furn;
import com.entity.Page;
import com.service.FurnService;
import com.service.impl.FurnServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class FurnServiceTest {
    private FurnService furnService = new FurnServiceImpl();

    @Test
    public void queryFurns() {
        List<Furn> furns = furnService.queryFurns();
        System.out.println(furns);
    }

    @Test
    public void addFurn() {
        int i = furnService.addFurn(new Furn(null, "world", "mary", BigDecimal.valueOf(20), 20, 21, "path321"));
        if (i == 1) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    public void deleteFurn() {
        if (furnService.deleteFurnById(16) >= 1) {
            System.out.println("已删除");
        } else {
            System.out.println("未能删除");
        }
    }

    @Test
    public void queryFurnByIdTest() {
        Furn furn = furnService.queryFurnById(1);
        System.out.println(furn);
    }

    @Test
    public void updateFurnTest() {
        int i = furnService.UpdateFurn(new Furn(20, "ssm", "springboot", new BigDecimal(221), 22, 11, null));
        if (i == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    public void currentPageAndFurnsTest() {
        Page<Furn> page = furnService.currentPageAndFurns(2, 4);
        System.out.println(page);
    }

    @Test
    public void pageSearchFurnByNameTest() {
        Page<Furn> furnPage = furnService.pageSearchFurnByName("a", 1, 4);
        System.out.println(furnPage);
    }
}
