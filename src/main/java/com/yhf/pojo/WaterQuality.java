package com.yhf.pojo;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 水质终端
 */
@Data
@Component
public class WaterQuality {
    /**
     * 所属传感器id
     */
    private Integer se_id;
//    数据上报时间
    private Timestamp wq_ctime;
    //    电池电压
    private float bat_volt;
    //    DO
    private float ec_do;
    //    PH
    private float ec_ph;
    //    温度
    private float temp;
    //    orp
    private float ec_orp;
    //   电导率
    private float ec_us;
    //    溶解固体物
    private float tds;
    //    盐度
    private float salt;
    //    溶解氧电压
    private float tdov;
    //    溶解氧饱和度
    private float tdos;

    //数据的时分秒，从wq_ctime中分割出的
    private String Minutesandseconds;

    public void setWq_ctime(Timestamp wq_ctime) {
        this.wq_ctime = wq_ctime;
        String str=this.wq_ctime.toString();
        String hour = String.valueOf(Integer.parseInt(str.substring(11, 13)));
        String minute = str.substring(14, 16);
        String seconds = str.substring(17,19);
        this.setMinutesandseconds(hour+":"+minute+":"+seconds);
    }
}
