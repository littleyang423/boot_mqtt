package com.yhf.util.impl;

import com.yhf.pojo.WaterQuality;
import com.yhf.pojo.WeatherStation;
import com.yhf.util.WeatherStationUtil;
import org.springframework.stereotype.Component;

/**
 * @author yhf
 * @date -
 */
@Component
public class WeatherStationUtilImpl implements WeatherStationUtil {
    private   byte[] bytes = new byte[100];
    private  int len = 0;
    private  int diff = 256;
//温度
    private  byte weather05 = 0x05;
//湿度
    private  byte weather06 = 0x06;
//光照
    private  byte weather07 = 0x07;
//风向
    private  byte weather08 = 0x08;
//风速
    private  byte weather09 = 0x09;
//24小时降雨量
    private  byte weather0A = 0x0A;

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

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public WeatherStation setBytes(byte[] byt) {
        bytes = byt;
        len = bytes.length;
        return analysis();
    }

    @Override
    public WeatherStation analysis(){
        if(bytes[3] == 0x01){
            int value = 0;
            for(int i = 4; i < len;){
                if(i >= len - 2){
                    System.out.println("break");
                    break;
                }
                value = 0;
                for(int j = 0; j < (bytes[i+1]&0xff); j++){
                    int temp = (bytes[j+i+2]&0xff);
                    System.out.print(temp+" ");
                    if(j > 0){
                        for(int k = 0; k < j; k++){
                            temp*=diff;
                        }
                    }
                    value+=temp;
                }
                if(bytes[i] == weather05){
                    if(value/10.0 >= 100.0){
                        int t = 1;
                        for(int j = 0; j < bytes[i+1]; j++){
                            t*=256;
                        }
                        value = t - value;
                    }
                    temp = (float) (value/10.0);
                }else if(bytes[i] == weather06){
                    humi = (float)(value/10.0);
                }else if(bytes[i] == weather07){
                    illu = (float)(value/10.0);
                }else if(bytes[i] == weather08){
                    wind_spd = (float)(value/10.0);
                }else if(bytes[i] == weather09){
                    wind_Drct = (float) (value/10.0);
                }else if(bytes[i] == weather0A){
                    rain_24hr = (float) (value/10.0);
                }
                i=i+2+bytes[i+1];
            }

            WeatherStation weatherStation = new WeatherStation();
            weatherStation.setHumi(humi);
            weatherStation.setIllu(illu);
            weatherStation.setRain_24hr(rain_24hr);
            weatherStation.setTemp(temp);
            weatherStation.setWind_Drct(wind_Drct);
            weatherStation.setWind_spd(wind_spd);
            return weatherStation;
        }
        return null;
    }
}
