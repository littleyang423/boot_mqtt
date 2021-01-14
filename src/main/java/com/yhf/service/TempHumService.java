package com.yhf.service;

import com.yhf.pojo.TempHum;
import com.yhf.pojo.WaterQuality;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
public interface TempHumService {

    void addMsg(TempHum tempHum);

    List<TempHum> getNewMsg(int n);

    List<TempHum> getMsgInScope(Timestamp beforeTime, Timestamp afterTime);

    boolean checkParam(int num);

    boolean checkParam(String beforetime);
}
