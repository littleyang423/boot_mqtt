package com.yhf.dao;

import com.yhf.pojo.Sensor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Mapper
@Repository
public interface SensorDao {
    /**
     *获取指定ip和端口的订阅主题
     */
    List<Sensor> getAllTopic(String ip, String port);

    /**
     * 根据订阅主题获取信息所属传感器id
     */
    Integer getSidByTheme(String theme);

    /**
     * 根据生产单元id获取指定生产单元的所有传感器
     * @return
     */
    List<Sensor> getSensorByPuid(Integer id);

    /**
     * 获取指定传感器的所属生产单元id
     */
    Integer getPuid(Integer id);
}
