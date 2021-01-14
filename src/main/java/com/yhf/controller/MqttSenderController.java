package com.yhf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhf.config.IMqttSender;
import com.yhf.pojo.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhf
 * @date -
 */
@RestController
public class MqttSenderController {

    @Autowired
    private IMqttSender imqttSender;

    @RequestMapping("/test1")
    public void test1(@RequestBody SendMsg sendMsg){
        imqttSender.sendToMqtt(JSON.toJSONString(sendMsg));
        System.out.println(JSON.toJSONString(sendMsg));
    }

    @RequestMapping("/test2/{tpoic}/{data}")
    public void test2(@PathVariable("topic") String topic,@PathVariable("data") String data){
        imqttSender.sendToMqtt(topic,data);
    }


}
