package com.yhf.service.impl;

import com.yhf.dao.TempHumDao;
import com.yhf.pojo.TempHum;
import com.yhf.pojo.WaterQuality;
import com.yhf.service.TempHumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Service
public class TempHumServiceImpl implements TempHumService {
    @Autowired
    private TempHumDao tempHumDao;

    @Value("${maxmsg.temphum}")
    private int maxNum;


    @Override
    public void addMsg(TempHum tempHum) {
        int msgNum = tempHumDao.getMsgNum();
        if(msgNum >= maxNum){
            tempHumDao.deleteOldestMsg(tempHumDao.getOldestMsg());
        }
        tempHumDao.addMsg(tempHum);
    }

    @Override
    public List<TempHum> getNewMsg(int n) {
        return tempHumDao.getNewMsg(n);
    }

    @Override
    public List<TempHum> getMsgInScope(Timestamp beforeTime, Timestamp afterTime) {
        List<TempHum> msgInScope = tempHumDao.getMsgInScope(beforeTime, afterTime);
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
