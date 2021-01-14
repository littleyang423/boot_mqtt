package com.yhf.controller;

import com.yhf.pojo.ResultEntity;
import com.yhf.pojo.WaterQuality;
import com.yhf.pojo.WeatherStation;
import com.yhf.service.WaterQualityService;
import com.yhf.service.WeatherStationService;
import com.yhf.util.impl.ResultUtil;
import com.yhf.util.impl.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
@RestController
@RequestMapping("/weather_station")
public class WeatherStationController {
    @Autowired
    private WeatherStationService weatherStationService;

    @GetMapping("/msgs")
    public ResultEntity getWaterMsg(@RequestParam(value = "num",required = false)int num){
        List<WeatherStation> newMsg = weatherStationService.getNewMsg(num);
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
//        List<WeatherStation> msgInScope = weatherStationService.getMsgInScope(beforeTime, afterTime);
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
//        if(!weatherStationService.checkParam(times)){
//            return ResultUtil.pwdError();
//        }

         
        Timestamp beforetime = TimeUtil.StringToTimp(times, false);
        Timestamp aftertime = TimeUtil.StringToTimp(times, true);
        List<WeatherStation> msgInScope = weatherStationService.getMsgInScope(beforetime, aftertime);
        if(msgInScope.size() == 0){
            return ResultUtil.dataIsNull();
        }
        return ResultUtil.success(msgInScope);
    }

}
