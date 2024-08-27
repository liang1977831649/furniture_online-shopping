package com.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DataUtils {
    public  static<T> T copyParamToBean(Map map,T bean){
        try {
            BeanUtils.populate(bean,map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    public static String createOrderId(){
        Date date = new Date();
        String format = new SimpleDateFormat("YYYYMMddHHmmss").format(date);
        String order=Math.round(Math.random()*10000)+"";
        return format+order;
    }

}
