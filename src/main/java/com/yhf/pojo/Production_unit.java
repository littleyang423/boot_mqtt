package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author yhf
 * @date -
 */
@Data
@Component
public class Production_unit {
    /**
     * 生产单元标识，id号
     */
    private int pu_id;
    /**
     * 生产单元名称
     */
    private String pu_name;
    /**
     * 生产单元长度
     */
    private float length;
    /**
     * 生产单元宽度
     */
    private float width;
    /**
     * 经度
     */
    private float longitude;
    /**
     * 纬度
     */
    private float latitude;
    /**
     * 产品类型id
     */
    private int pt_id;
    /**
     * 产品名称
     */
    private String pt_name;
    /**
     * 生产单元创建时间
     */
    private Timestamp pu_ctime;
    /**
     * 创建者id，如果是0就代表是系统默认创建
     */
    private int creator_id;



}
