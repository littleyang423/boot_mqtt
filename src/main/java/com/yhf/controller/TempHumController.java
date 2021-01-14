package com.yhf.controller;

import com.yhf.pojo.ResultEntity;
import com.yhf.pojo.TempHum;
import com.yhf.pojo.WaterQuality;
import com.yhf.service.TempHumService;
import com.yhf.util.impl.ResultUtil;
import com.yhf.util.impl.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/temp_hum")
public class TempHumController {
    @Autowired
    private TempHumService tempHumService;

    @GetMapping("/msgs")
    public ResultEntity getTempHumMsg(@RequestParam(value = "num",required = false)Integer num){
        if(!tempHumService.checkParam(num)){
            return ResultUtil.ParamError();
        }

        List<TempHum> newMsg = tempHumService.getNewMsg(num);
        if(newMsg.size() == 0){
            return ResultUtil.dataIsNull();
        }
        return ResultUtil.success(newMsg);
    }


    /**
     * 获取指定时间段的数据
     */
    @GetMapping("day_msgs")
    public ResultEntity getWaterMsgOneDay(@RequestParam(value = "times",required = false)String times){
        Timestamp beforetime = TimeUtil.StringToTimp(times, false);
        Timestamp aftertime = TimeUtil.StringToTimp(times, true);
        List<TempHum> msgInScope = tempHumService.getMsgInScope(beforetime, aftertime);
        if(msgInScope.size() == 0){
            return ResultUtil.dataIsNull();
        }
        return ResultUtil.success(msgInScope);
    }

}
