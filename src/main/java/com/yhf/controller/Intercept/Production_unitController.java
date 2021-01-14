package com.yhf.controller.Intercept;

import com.yhf.pojo.Production_unit;
import com.yhf.pojo.ResultEntity;
import com.yhf.service.Production_unitService;
import com.yhf.util.impl.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yhf
 * @date -
 */
@RestController
@RequestMapping("/production_unit")
public class Production_unitController {

    @Autowired
    private Production_unitService production_unitService;

    @GetMapping("/units")
    public ResultEntity getAllProduction_units(){
        List<Production_unit> allUnit = production_unitService.getAllUnit();
        if(allUnit == null){
            return ResultUtil.dataIsNull();
        }
        return ResultUtil.success(allUnit);
    }
}
