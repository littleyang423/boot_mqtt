package com.yhf.service;

import com.yhf.pojo.Equipment;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
public interface EquipmentService {
    List<Equipment> getAllEquip();
    void changeStatus(Equipment equipment);
    int getEquipId(String name);
}
