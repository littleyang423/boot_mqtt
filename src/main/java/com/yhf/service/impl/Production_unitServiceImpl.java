package com.yhf.service.impl;

import com.yhf.dao.Production_unitDao;
import com.yhf.pojo.Production_unit;
import com.yhf.service.Production_unitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
@Service
public class Production_unitServiceImpl implements Production_unitService {

    @Autowired
    private Production_unitDao production_unitDao;

    @Override
    public List<Production_unit> getAllUnit() {
        List<Production_unit> allProduction = production_unitDao.getAllProduction();
        return allProduction;
    }
}
