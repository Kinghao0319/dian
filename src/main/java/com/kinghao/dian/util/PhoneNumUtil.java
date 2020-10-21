package com.kinghao.dian.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Kinghao
 * @Date 2020/8/4 19:20
 * @Version 1.0
 */
public class PhoneNumUtil {
    public static boolean checkValid(String phone){
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
            return false;
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
