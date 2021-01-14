package com.yhf.dao;

import com.yhf.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Mapper
@Repository
public interface EquipDao {

    /**
     * 获取所有设备
     */
    public List<Equipment> getAllEquip();

    void changeStatus(@Param("em") Equipment equipment);

    int getEquipId(String name);
}
