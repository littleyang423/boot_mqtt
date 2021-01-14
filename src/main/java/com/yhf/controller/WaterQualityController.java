package com.yhf.controller;

import com.yhf.pojo.ResultEntity;
import com.yhf.pojo.WaterQuality;
import com.yhf.service.WaterQualityService;
import com.yhf.util.impl.ResultUtil;
import com.yhf.util.impl.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
@RestController
@RequestMapping("/water_quality")
public class WaterQualityController {

    @Autowired
    private WaterQualityService waterQualityService;

    @GetMapping("/msgs")
    public ResultEntity getWaterMsg(@RequestParam(value = "num" ,required = false)int num){
        if(!waterQualityService.checkParam(num)){
            return ResultUtil.ParamError();
        }

        List<WaterQuality> newMsg = waterQualityService.getNewMsg(num);
        if(newMsg.size() == 0){
            return ResultUtil.dataIsNull();
        }
        return ResultUtil.success(newMsg);
    }

//    /**
//     * 获取指定时间段的数据
//     */
//    @GetMapping("/scope_msgs")
//    public ResultEntity getWaterMsgInScope(@RequestParam("beforetime")String beforeTime,@RequestParam("aftertime") String afterTime){
//        if(!waterQualityService.checkParam(beforeTime)){
//            return ResultUtil.ParamError();
//        }
//        if(!waterQualityService.checkParam(afterTime)){
//            return ResultUtil.ParamError();
//        }
//        List<WaterQuality> msgInScope = waterQualityService.getMsgInScope(beforeTime, afterTime);
//        if(msgInScope == null){
//            return ResultUtil.dataIsNull();
//        }
//        return ResultUtil.success(msgInScope);
//    }

    /**
     * 获取指定时间段的数据
     */
    @GetMapping("day_msgs")
    public ResultEntity getWaterMsgOneDay(@RequestParam(value = "times",required = false)String times){

//        if(!waterQualityService.checkParam(times)){
////            Timestamp t = new Timestamp(System.currentTimeMillis());
////
////            Timestamp beforetime = TimeUtil.StringToTimp(times, false);
////            Timestamp aftertime = TimeUtil.StringToTimp(times, true);
//            return ResultUtil.pwdError();
//        }
        Timestamp beforetime = TimeUtil.StringToTimp(times, false);
        Timestamp aftertime = TimeUtil.StringToTimp(times, true);
        List<WaterQuality> msgInScope = waterQualityService.getMsgInScope(beforetime, aftertime);
//        System.out.println(beforetime);
//        System.out.println(aftertime);
        if(msgInScope.size() == 0){
            return ResultUtil.dataIsNull();
        }
        return ResultUtil.success(msgInScope);
    }

}
