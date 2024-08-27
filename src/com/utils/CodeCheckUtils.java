package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeCheckUtils {
    /**
     * 返回数字类型
     * 返回0，表示验证码错误
     * 返回1，表示验证码已过期
     * 返回3，表示正常通过
     *
     * @param kapcha
     * @param code
     * @param
     * @return
     */
    public static int checkCode(String kapcha, String code, Object kapcheDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //(1)simpleDateFormat.format(kaptchaSessionDate)==xxx是将Object的时间转化成字符串
        //(2)simpleDateFormat.parse(xxx)是将字符串转成Date类对象的时间对象
        //(3)new Date().getTime()是拿到当前的时间
        int dateDeduct = (int) ((new Date().getTime() - simpleDateFormat.parse(simpleDateFormat.format(kapcheDate)).getTime()) / 1000);
        //验证码的校验
        //内容校验(忽略大小写)
        if (!kapcha.equalsIgnoreCase(code)) {
            return 0;
        }
        if (dateDeduct >= 60) {
            return 1;
        }
        return 2;
    }
}
