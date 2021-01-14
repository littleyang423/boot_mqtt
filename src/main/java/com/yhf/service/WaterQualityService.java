package com.yhf.service;

import com.yhf.pojo.WaterQuality;

import java.sql.Timestamp;
import java.util.List;

/**
 * 水质终端
 */
public interface WaterQualityService {
    void addMsg(WaterQuality waterQuality);

    List<WaterQuality> getNewMsg(int n);

    List<WaterQuality> getMsgInScope(Timestamp beforeTime, Timestamp afterTime);

    boolean checkParam(int num);

    boolean checkParam(String beforetime);

}
