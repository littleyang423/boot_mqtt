package com.yhf.controller;

import com.alibaba.fastjson.JSON;
import com.yhf.config.IMqttSender;
import com.yhf.pojo.ControllerEquip;
import com.yhf.pojo.Equipment;
import com.yhf.pojo.ResultEntity;
import com.yhf.pojo.SendMsg;
import com.yhf.service.EquipmentService;
import com.yhf.util.EquipUtil;
import com.yhf.util.impl.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

/**
 * @author yhf
 * @date -
 */
@RestController
@RequestMapping("/control_equip")
public class EquipController {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private IMqttSender imqttSender;

    /**
     * 用来接收控制传感器时接收到的传感器的返回值，如果为1代表成功，2代表失败响应超时，3代表执行成功 02 代表485控制响应失败
     */
    int status = 0;
    byte b;

    byte[] bytes=new byte[9];
    public void filling(ControllerEquip controllerEquip){
        String equipName = controllerEquip.getPropSwitch();
        boolean status = controllerEquip.isStatus();
        bytes[0] = (byte) 0xFE;
        bytes[1] = 0x06;
        bytes[2] = 0x01;
        bytes[3] = (byte) 0xE2;
        bytes[4] = 0x01;
        int equipId = equipmentService.getEquipId(equipName);
        switch (equipId){
            case 1:
                bytes[5]=0x01;
                break;
            case 2:
                bytes[5]=0x02;
                break;
            case 3:
                bytes[5]=0x03;
                break;
            case 4:
                bytes[5]=0x04;
                break;
            case 5:
                bytes[5]=0x05;
                break;
            case 6:
                bytes[5]=0x06;
                break;
            case 7:
                bytes[5]=0x07;
                break;
            case 8:
                bytes[5]=0x08;
                break;
            default:
                break;
        }

        if(status == true){
            bytes[6] = 0x01;
        }else{
            bytes[6] = 0x00;
        }
        bytes[7] = 0x00;
        bytes[8] = (byte) 0xEF;

    }

    @PostMapping("/equip")
    public ResultEntity controlEquip01(@RequestBody ControllerEquip controllerEquip) {
        filling(controllerEquip);
        SendMsg sendMsg = new SendMsg();
        sendMsg.setComfirmed(false);
        sendMsg.setfPort(10);
        sendMsg.setData(Base64.getEncoder().encodeToString(bytes));
        imqttSender.sendToMqtt(JSON.toJSONString(sendMsg));
        int a=status;
        System.out.println("a="+status);
        while(a == status){

        }
        ResultEntity success = ResultUtil.success();
        Equipment equipment = new Equipment();
        if(b == 0x00){
            equipment.setStatus(true);
            success.setMsg("成功!");
        }else if(b==0x01){
            equipment.setStatus(false);
            success.setMsg("响应超时!");
        }else if(b==0x02){
            equipment.setStatus(false);
            success.setMsg("控制失败!");
        }
        System.out.println(success.getMsg());
        equipment.setPropSwitch(controllerEquip.getPropSwitch());
        success.setData(equipment);
        if(equipment.isStatus() == true){
            Equipment e = new Equipment();
            e.setStatus(controllerEquip.isStatus());
            e.setPropSwitch(controllerEquip.getPropSwitch());
            equipmentService.changeStatus(e);
        }
        return success;
    }

    public void setStatus(byte b){
        System.out.println("setstatus");
        status++;
        System.out.println("status="+status);
        this.b=b;
    }

    @GetMapping("/equips")
    public List<Equipment> getAllEquipment(){
        List<Equipment> allEquip = equipmentService.getAllEquip();
        return allEquip;
    }



}
