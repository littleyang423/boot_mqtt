package com.yhf.dao;

import com.yhf.pojo.Illu;
import com.yhf.pojo.WaterQuality;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Mapper
@Repository
public interface IlluDao {

    /**
     * 添加数据
     */
    void addMsg(@Param("il") Illu illu);

    /**
     * 获取数据库中数据的条目数
     */
    int getMsgNum();

    /**
     * 删除最早的一条数据
     */
    void deleteOldestMsg(int id);

    /**
     * 获取最新n的一条数据
     */
    List<Illu> getNewMsg(int num);

    /**
     * 获取最早的一条数据的id
     */
    int getOldestMsg();


    /**
     * 获取指定时间返回内的数据
     * 时间格式为 yyyy-MM-dd HH-mm-ss
     */
    List<Illu> getMsgInScope(Timestamp BeforeTime, Timestamp AfterTime);

}
