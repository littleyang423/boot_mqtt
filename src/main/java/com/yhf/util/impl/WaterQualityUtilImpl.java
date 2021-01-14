package com.yhf.util.impl;

import com.yhf.pojo.WaterQuality;
import com.yhf.util.WaterQualityUtil;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @author yhf
 * @date -
 */
@Component
public class WaterQualityUtilImpl implements WaterQualityUtil {
    private  byte[] bytes = new byte[100];
    private  int len = 0;
    private  int diff = 256;
//    电池电压
    private  byte water01 = 0x01;
//    DO
    private  byte water02 = 0x02;
//    PH
    private  byte water03 = 0x03;
//    温度
    private  byte water04 = 0x04;
//    orp
    private  byte water0b = 0x0b;
//    电导率
    private  byte water0c = 0x0c;
//    溶解固体物
    private  byte water0d = 0x0d;
//盐度
    private  byte water0e = 0x0e;
//溶解氧电压
    private  byte water0f = 0x0f;
//溶解氧饱和度
    private  byte water10 = 0x10;
//溶解氧电压数字数
    private  byte water11 = 0x11;
//PH电压数字量
    private  byte water12 = 0x12;
//PH电压数字量
    private  byte water13 = 0x13;
//8路或16路继电器状态
    private  byte water14 = 0x14;


    //    电池电压
    private  float bat_volt;
    //    DO
    private  float ec_do;
    //    PH
    private  float ec_ph;
    //    温度
    private  float temp;
    //    orp
    private  float ec_orp;
    //   电导率
    private  float ec_us;
    //    溶解固体物
    private  float tds;
    //    盐度
    private  float salt;
    //    溶解氧电压
    private  float tdov;
    //    溶解氧饱和度
    private  float tdos;


    @Override
    public  byte[] getBytes() {
        return bytes;
    }

    @Override
    public  WaterQuality setBytes(byte[] byt) {
        bytes = byt;
        len = bytes.length;
//        System.out.println("bytes:::"+printHexBinary(bytes));
//        System.out.println(bytes.length);
//        for(int i = 0;i < len; i++){
//            System.out.print((bytes[i]&0xff)+" ");
//        }
        return analysis();
    }
    @Override
    public  WaterQuality analysis(){
        if(bytes[3] == 0x01){
            int value = 0;
            for(int i = 4; i < len;){
                if(i >= len - 2){
                    System.out.println("break");
                    break;
                }
                value = 0;
                for(int j = 0; j < (bytes[i+1]&0xff); j++){
                    int temp1 = (bytes[j+i+2]&0xff);
                    System.out.print(temp1 + " ");
                    if(j > 0){
                        for(int k = 0; k < j; k++){
                            temp1*=diff;
                        }
                    }
                    value += temp1;
                }
                if(bytes[i] == water01){
                    bat_volt = (float) (value/10.0);
//                    System.out.println("0x01 电池电压 "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water02){
                    ec_do = (float) (value/10.0);
//                    System.out.println("0x02 DO "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water03){
                    ec_ph = (float) (value/10.0);
//                    System.out.println("0x03 PH "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water04){
                    if(value/10.0 >= 100.0){
                        int t = 1;
                        for(int j = 0; j < bytes[i+1]; j++){
                            t*=256;
                        }
                        value = t - value;
                    }
                    temp = (float) (value/10.0);
//                    System.out.println("0x04 温度 "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water0b){
                    ec_orp = (float) (value/10.0);
//                    System.out.println("0x0b orp "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water0c){
                    ec_us = (float) (value/10.0);
//                    System.out.println("0x0c 电导率ec_us "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water0d){
                    tds = (float) (value/10.0);
//                    System.out.println("0x0d 溶解固体物tds "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water0e){
                    salt = (float) (value/10.0);
//                    System.out.println("0x0e 盐度salt "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water0f){
                    tdov = (float) (value/10.0);
//                    System.out.println("0x0f 溶解氧电压 "+bytes[i+1]+" :"+value/10.0);
                }else if(bytes[i] == water10){
                    tdos = (float) (value/10.0);
//                    System.out.println("0x10 溶解氧饱和度 "+bytes[i+1]+" :"+value/10.0);
                }
                i=i+2+bytes[i+1];
            }
            if(ec_orp==0||ec_us==0||salt==0||tds==0){
                return null;
            }
            if(ec_orp==6553.5||ec_us==6553.5||salt == 6553.5||tds == 6553.5){
                return null;
            }
            WaterQuality waterQuality = new WaterQuality();
            waterQuality.setBat_volt(bat_volt);
            waterQuality.setEc_do(ec_do);
            waterQuality.setEc_orp(ec_orp);
            waterQuality.setEc_ph(ec_ph);
            waterQuality.setEc_us(ec_us);
            waterQuality.setSalt(salt);
            waterQuality.setTdos(tdos);
            waterQuality.setTdov(tdov);
            waterQuality.setTds(tds);
            waterQuality.setTemp(temp);

            return waterQuality;
        }
        return null;
    }
}
