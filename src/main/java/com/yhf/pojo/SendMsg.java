package com.yhf.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yhf
 * @date -
 */
@Data
@Component
public class SendMsg {
    private boolean comfirmed;
    private int fPort;
    private String data;

    public void setfPort(int fPort) {
        this.fPort = 10;
    }

    @Override
    public String toString() {
        return "{" +
                "comfirmed=" + comfirmed +
                ", fPort=" + fPort +
                ", data=\"" + data + '\"' +
                '}';
    }
}
