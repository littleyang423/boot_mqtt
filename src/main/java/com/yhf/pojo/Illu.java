package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author yhf
 * @date -
 */
@Data
@Component
public class Illu {
    private String Minutesandseconds;
    /**
     * 光照
     */
    private float illu;
    private int se_id;
    private Timestamp il_ctime;
    //数据的时分秒，从wq_ctime中分割出的


    public void setIl_ctime(Timestamp il_ctime) {
        this.il_ctime = il_ctime;
        String str=this.il_ctime.toString();
        String hour = String.valueOf(Integer.parseInt(str.substring(11, 13)));
        String minute = str.substring(14, 16);
        String seconds = str.substring(17,19);
        this.setMinutesandseconds(hour+":"+minute+":"+seconds);
    }
}
