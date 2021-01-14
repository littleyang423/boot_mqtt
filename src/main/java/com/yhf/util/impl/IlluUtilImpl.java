package com.yhf.util.impl;

import com.yhf.pojo.Illu;
import com.yhf.util.IlluUtil;
import org.springframework.stereotype.Component;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @author yhf
 * @date -
 */
@Component
public class IlluUtilImpl implements IlluUtil {
    private  byte[] bytes = new byte[100];
    private  int len = 0;
    private  int diff = 256;

    private byte illu_left = 0x03;
    private byte illu_right = 0x10;

    private float illu = 0;


    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public Illu setBytes(byte[] byt) {
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
    public Illu analysis() {
        if(bytes[0] == 0x01){
            float illuvalue = 0;
            for(int i = 1; i < len-2; i ++){
                if(bytes[i] == 0x03&& bytes[i+1]==0x10){
                    for(int j =i+2; j<i+6; j++){
                        int t = (bytes[j]&0xff);
                        for(int k = 0; k < j-i-2; k++){
                            t*=diff;
                        }
                        illuvalue+=t;
                    }
                }
            }
            illu= (float) (illuvalue/1000.0);
            Illu illu2 = new Illu();
            illu2.setIllu(illu);
            return illu2;
        }
        return null;
    }
}
