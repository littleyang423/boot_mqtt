package com.yhf.util.impl;

import com.yhf.pojo.TempHum;
import com.yhf.util.TempHumUtil;
import org.springframework.stereotype.Component;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @author yhf
 * @date -
 */
@Component
public class TempHumUtilImpl implements TempHumUtil {

    private  byte[] bytes = new byte[100];
    private  int len = 0;
    private  int diff = 256;

    private byte TempLeft = 0x01;
    private byte TempRight = 0x10;
    private byte HumLeft = 0x02;
    private byte HumRight = 0x10;

    /**
     * 温度
     */
    private float temp = 0;
    /**
     * 湿度
     */
    private float hum = 0;

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public TempHum setBytes(byte[] byt) {
        bytes = byt;
        len = bytes.length;
        System.out.println("bytes:::"+printHexBinary(bytes));
        System.out.println(bytes.length);
        for(int i = 0;i < len; i++){
            System.out.print((bytes[i]&0xff)+" ");
        }
        return analysis();
    }

    @Override
    public TempHum analysis() {
        if(bytes[0] == 0x01){
            float tempvalue = 0;
            float humvalue = 0;
            for(int i = 1;i < len; i++){
                if(bytes[i] == TempLeft && bytes[i+1] == TempRight){
                    for(int j = i+2; j < i+6 ; j++){
                        int t = (bytes[j]&0xff);
                        for(int k = 0; k < j-i-2; k++){
                            t*=diff;
                        }
                        tempvalue+=t;
                    }
                }
                if(bytes[i] == HumLeft && bytes[i+1] == HumRight){
                    for(int j = i+2; j < i+6; j++){
                        int t = (bytes[j]&0xff);
                        for(int k = 0; k < j-i-2; k++){
                            t*=diff;
                        }
                        humvalue+=t;
                    }
                }
                i+=6;
            }

            temp = (float) (tempvalue/1000.0);
            hum = (float) (humvalue/1000.0);
            TempHum tempHum = new TempHum();
            tempHum.setTh_hum(hum);
            tempHum.setTh_temp(temp);
            return tempHum;
        }
        return null;
    }
}
