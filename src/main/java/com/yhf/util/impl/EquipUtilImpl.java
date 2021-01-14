package com.yhf.util.impl;

import com.yhf.controller.EquipController;
import com.yhf.pojo.Illu;
import com.yhf.util.EquipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

/**
 * @author yhf
 * @date -
 */
@Component
public class EquipUtilImpl implements EquipUtil {
    private  byte[] bytes = new byte[100];
    private  int len = 0;

    @Autowired
    private EquipController equipController;

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public void setBytes(byte[] byt) {
        bytes = byt;
        len = bytes.length;
        System.out.println("bytes:::"+printHexBinary(bytes));
        System.out.println(bytes.length);
        for(int i = 0;i < len; i++){
            System.out.print((bytes[i]&0xff)+" ");
        }
        analysis();
    }

    @Override
    public void analysis() {
        equipController.setStatus(bytes[4]);
    }
}
