package com.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateTimeUtils {
    public static String getDateTimeNow(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
