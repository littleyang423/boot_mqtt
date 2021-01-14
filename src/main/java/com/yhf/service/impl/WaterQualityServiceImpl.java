package com.yhf.service.impl;

import com.yhf.dao.WaterQualityDao;
import com.yhf.pojo.WaterQuality;
import com.yhf.service.WaterQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * 水质终端
 */
@Service
public class WaterQualityServiceImpl implements WaterQualityService {
    @Autowired
    private WaterQualityDao waterQualityDao;

    @Value("${maxmsg.waterQuality}")
    private int maxNum;
    /**
     * 向数据库中添加水质终端消息数据，数据库中最多存储500条数据,当数据条数大于等于500时，自动删除最久的一条数据
     */
    @Override
    public void addMsg(WaterQuality waterQuality) {
        int msgNum = waterQualityDao.getMsgNum();
        if(msgNum >= maxNum){
            waterQualityDao.deleteOldestMsg(waterQualityDao.getOldestMsg());
        }
        waterQualityDao.addMsg(waterQuality);
    }

    @Override
    public List<WaterQuality> getNewMsg(int n) {
        return waterQualityDao.getNewMsg(n);
    }

    @Override
    public List<WaterQuality> getMsgInScope(Timestamp beforeTime, Timestamp afterTime) {
        List<WaterQuality> msgInScope = waterQualityDao.getMsgInScope(beforeTime, afterTime);
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
