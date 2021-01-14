package com.yhf.service.impl;

import com.yhf.dao.IlluDao;
import com.yhf.pojo.Illu;
import com.yhf.pojo.WaterQuality;
import com.yhf.service.IlluService;
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
public class IlluServiceImpl implements IlluService {
    @Autowired
    private IlluDao illuDao;
    @Value("${maxmsg.illu}")
    private int maxNum;
    @Override
    public void addMsg(Illu illu) {
        int msgNum = illuDao.getMsgNum();
        if(msgNum >= maxNum){
            illuDao.deleteOldestMsg(illuDao.getOldestMsg());
        }
        illuDao.addMsg(illu);
    }

    @Override
    public List<Illu> getNewMsg(int n) {
        return illuDao.getNewMsg(n);
    }

    @Override
    public List<Illu> getMsgInScope(Timestamp beforeTime, Timestamp afterTime) {
        List<Illu> msgInScope = illuDao.getMsgInScope(beforeTime, afterTime);
        return msgInScope;    }

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
