package com.yhf.service.impl;

import com.yhf.dao.EquipDao;
import com.yhf.pojo.Equipment;
import com.yhf.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipDao equipDao;

    @Override
    public List<Equipment> getAllEquip() {
        List<Equipment> allEquip = equipDao.getAllEquip();
        return allEquip;
    }

    @Override
    public void changeStatus(Equipment equipment) {
        equipDao.changeStatus(equipment);
    }

    @Override
    public int getEquipId(String name) {
        return equipDao.getEquipId(name);
    }
}
