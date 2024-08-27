package com.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestOther {
//    @Test
//    public void m(){
//        String num="-123";
//        String regx="[0-9]+";
//        if(Pattern.matches(regx, num)){
//            System.out.println("验证通过");
//        }else{
//            System.out.println("不通过");
//        }
//    }
//    @Test
//    public void m2(){
//        String num="3454.00";
//        String regx="[0-9]+(\\.[0-9]+)?";
//        if(Pattern.matches(regx, num)){
//            System.out.println("验证通过");
//        }else{
//            System.out.println("不通过");
//        }
//    }
//    @Test
//    public void m3(){
//        int furnCount=12;
//        String json="{\"msg\":\"ok\",\"furncount\":\""+furnCount+"\"}";
//        System.out.println(json);
//    }
//    @Test
//    public void m4(){
//        int total=100,allTotal=200,allCount=12;
//        String json="{\"msg\":\"success\",\"total\":\""+total+"\",\"allTotal\":\""+allTotal+"\",\"allCount\":\""+allCount+"\"}";
//        System.out.println(json);
//    }

    @Test
    public void m5(){
        int allTotal=0;
        int allCount=0;
        String json="{\"msg\":\"ok\",\"allTotal\":\""+allTotal+"\",\"allCount\":\""+allCount+"\"}";
        System.out.println(json);
    }
    @Test
    public void m6(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
    @Test
    public void m7(){
        Date date = new Date();
        String format = new SimpleDateFormat("YYYYMMddHHmmss").format(date);
        String order=Math.round(Math.random()*10000)+"";
        order=format+order;
        System.out.println(order);
    }

    @Test
    public void m8(){
        int i=2;
        int begin=i%4==0?((i-1)/4)*4+1:(i/4)*4+1;
        System.out.println(begin);
    }
}
