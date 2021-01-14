package com.yhf.service.impl;

import com.yhf.dao.WeatherStationDao;
import com.yhf.pojo.WaterQuality;
import com.yhf.pojo.WeatherStation;
import com.yhf.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 气象站
 */
@Service
public class WeatherStationServiceImpl implements WeatherStationService {
    @Autowired
    private WeatherStationDao weatherStationDao;

    @Value("${maxmsg.weatherStation}")
    private int maxNum;

    @Override
    public void addMsg(WeatherStation weatherStation) {
        int msgNum = weatherStationDao.getMsgNum();
        if(msgNum >= maxNum){
            weatherStationDao.deleteOldestMsg(weatherStationDao.getOldestMsg());
        }
        weatherStationDao.addMsg(weatherStation);
    }

    @Override
    public List<WeatherStation> getNewMsg(int n) {
        return weatherStationDao.getNewMsg(n);
    }

    @Override
    public List<WeatherStation> getMsgInScope(Timestamp beforeTime, Timestamp afterTime) {
        List<WeatherStation> msgInScope = weatherStationDao.getMsgInScope(beforeTime, afterTime);
        return msgInScope;
    }

    @Override
    public boolean checkParam(int num) {
        if(num <= 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkParam(String beforetime) {
        if(beforetime == null){
            return false;
        }
        return true;
    }
}
