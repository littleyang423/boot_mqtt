package com.yhf.service;

import com.yhf.pojo.Illu;
import com.yhf.pojo.WaterQuality;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
public interface IlluService {
    void addMsg(Illu illu);

    List<Illu> getNewMsg(int n);

    List<Illu> getMsgInScope(Timestamp beforeTime, Timestamp afterTime);

    boolean checkParam(int num);

    boolean checkParam(String beforetime);
}
