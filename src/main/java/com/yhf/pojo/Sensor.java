package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 传感器
 */
@Data
@Component
public class Sensor {
    private Integer se_id;
    /**
     * 传感器名称
     */
    private String se_name;
    /**
     * 订阅主题
     */
    private String theme;
    /**
     * 订阅ip地址
     */
    private String host;
    /**
     * 订阅端口
     */
    private String port;
    /**
     * 所属生产单元id
     */
    private String pu_id;
    /**
     * 建立时间
     */
    private Timestamp se_ctime;
    /**
     * 继电器状态
     */
    private int status;

}
