package com.yhf.service;

/**
 * 传感器
 */
public interface SensorService {
    /**
     * 获取所有主题
     */
    String getThemes(String host,String port);

    /**
     * 获取传感器id
     */
    Integer getSid(String theme);

//    /**
//     * 获取传感器所属生产单元的id
//     */
//    Integer getTid(Integer id);
}
