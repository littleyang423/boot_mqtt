package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * @author yhf
 * @date -
 */

@Data
@Component
public class Equipment {
    /**
     * 设备状态
     */
    private boolean status;
    /**
     * 设备名称
     */
    private String propSwitch;
}
