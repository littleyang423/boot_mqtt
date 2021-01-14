package com.yhf.pojo;

/**
 * @author yhf
 * @date -
 */

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 实验室温湿度
 */
@Data
@Component
public class TempHum {
    /**
     * 温度
     */
    private float th_temp;

    /**
     * 湿度
     */
    private float th_hum;

    private Integer se_id;

    private Timestamp th_ctime;

    //数据的时分秒，从wq_ctime中分割出的
    private String Minutesandseconds;

    public void setTh_ctime(Timestamp th_ctime) {
        this.th_ctime = th_ctime;
        String str=this.th_ctime.toString();
        String hour = String.valueOf(Integer.parseInt(str.substring(11, 13)));
        String minute = str.substring(14, 16);
        String seconds = str.substring(17,19);
        this.setMinutesandseconds(hour+":"+minute+":"+seconds);
    }

}
