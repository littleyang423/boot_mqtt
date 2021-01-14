package com.yhf.pojo;

import org.springframework.stereotype.Component;

/**
 * 用来获取订阅数据
 */
@Component
public class JsonData {

    private String data;

    public JsonData() {
    }

    public JsonData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "data='" + data + '\'' +
                '}';
    }

    public void setData(String data) {
        this.data = data;
    }
}
