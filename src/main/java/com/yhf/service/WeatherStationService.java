package com.yhf.service;

import com.yhf.pojo.WaterQuality;
import com.yhf.pojo.WeatherStation;

import java.sql.Timestamp;
import java.util.List;

/**
 * 气象站
 */
public interface WeatherStationService {
    void addMsg(WeatherStation weatherStation);

    List<WeatherStation> getNewMsg(int n);

    List<WeatherStation> getMsgInScope(Timestamp beforeTime, Timestamp afterTime);

    boolean checkParam(int num);

    boolean checkParam(String beforetime);
}
