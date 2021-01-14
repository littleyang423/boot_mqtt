package com.yhf.dao;

import com.yhf.pojo.WaterQuality;
import com.yhf.pojo.WeatherStation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Mapper
@Repository
public interface WeatherStationDao {
    /**
     * 添加数据
     */
    void addMsg(@Param("ws") WeatherStation weatherStation);

    /**
     * 获取数据库中数据的条目数
     */
    int getMsgNum();

    /**
     * 删除最早的一条数据
     */
    void deleteOldestMsg(int id);

    /**
     * 获取最早的一条数据的id
     */
    int getOldestMsg();

    /**
     * 获取最新n的一条数据
     */
    List<WeatherStation> getNewMsg(int n);

    /**
     * 获取指定时间返回内的数据
     * 时间格式为 yyyy-MM-dd HH-mm-ss
     */
    List<WeatherStation> getMsgInScope(Timestamp BeforeTime, Timestamp AfterTime);

}
