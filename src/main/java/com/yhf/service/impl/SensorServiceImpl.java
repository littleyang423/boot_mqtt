package com.yhf.service.impl;

import com.yhf.dao.SensorDao;
import com.yhf.pojo.Sensor;
import com.yhf.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 传感器
 */
@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorDao sensorDao;

    @Override
    public String getThemes(String host, String port){
        String str="";
        List<Sensor> allTopic = sensorDao.getAllTopic(host, port);
        int len = allTopic.size();
        for(int i = 0; i < len; i++){
            if(i == 0){
                str = str + allTopic.get(i).getTheme();
            }else{
                str = str + "," + allTopic.get(i).getTheme();
            }
        }
        return str;
    }

    @Override
    public Integer getSid(String theme) {
        Integer sidByTheme = sensorDao.getSidByTheme(theme);
        return sidByTheme;
    }
//
//    @Override
//    public Integer getTid(Integer id) {
////        Integer tid = sensorDao.getTid(id);
//        return 0;
//    }


}
