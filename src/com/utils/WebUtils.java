package com.utils;

import java.time.LocalDateTime;

public class WebUtils {
    public static String getDateTimeString(){
        LocalDateTime now = LocalDateTime.now();
        String str=now.getYear()+"\\"+now.getMonthValue()+"\\";
        return str;
    }
}
