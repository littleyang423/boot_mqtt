package com.yhf.dao;

import com.yhf.pojo.Production_unit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Mapper
@Repository
public interface Production_unitDao {

    /**
     * 获取所有的生产单元
     */
    List<Production_unit> getAllProduction();

    /**
     * 根据指定的生产单元id获取信息
     */
    Production_unit getPojoById();
}
