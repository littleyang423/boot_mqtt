package com.yhf.util.impl;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @author yhf
 * @date -
 */
public class TimeUtil {
    private static HashMap<String,String> maps = new HashMap<>();
    static{
        maps.put("Jan", "01");
        maps.put("Feb", "02");
        maps.put("Mar", "03");
        maps.put("Apr", "04");
        maps.put("May", "05");
        maps.put("Jun", "06");
        maps.put("Jul", "07");
        maps.put("Aug", "08");
        maps.put("Sep", "09");
        maps.put("Oct", "10");
        maps.put("Nov", "11");
        maps.put("Dec", "12");
    }

    /**
     * 将前端传来的日期 格式化 为Timestamp格式，如果is为true代表获取23:59:59，如果为false代表获取00:00:00
     * 如果timp为null，则代表获取当天的数据
     * @param timp
     * @param is
     * @return
     */
    public static Timestamp StringToTimp(String timp,boolean is){
        boolean a=false;
        if(timp == null){
            Timestamp tss = new Timestamp(System.currentTimeMillis());
            timp = tss.toString();
            a=true;
        }
//        System.out.println("timp="+timp);
        String year;
        String mouth;
        String day;
        year = timp.substring(11, 15);
        mouth = maps.get(timp.substring(4, 7));
        day = timp.substring(8,10);
        String time;
        if(is){
            time = year + "-" + mouth + "-" + day + " 23:59:59";
            if(a){
                time = timp.substring(0, 10) + " 23:59:59";
            }
        }else{
            time = year + "-" + mouth + "-" + day + " 00:00:00";
            if(a){
                time = timp.substring(0, 10) + " 00:00:00";
            }
        }

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts = Timestamp.valueOf(time);
        return ts;
    }
}
