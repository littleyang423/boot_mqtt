package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author yhf
 * @date -
 */
@Data
@Component
public class WeatherStation {
    private int se_id;
    private Timestamp we_ctime;
    //温度
    private float temp;
    //湿度
    private float humi;
    //光照
    private float illu;
    //风向
    private float wind_spd;
    //风速
    private float wind_Drct;
    //24小时降水量
    private float rain_24hr;

    //数据的时分秒，从we_ctime中分割出的
    private String Minutesandseconds;

    public void setWe_ctime(Timestamp we_ctime) {
        this.we_ctime = we_ctime;
        String str=this.we_ctime.toString();
        String hour = String.valueOf(Integer.parseInt(str.substring(11, 13)));
        String minute = str.substring(14, 16);
        String seconds = str.substring(17,19);
        this.setMinutesandseconds(hour+":"+minute+":"+seconds);
    }
}
