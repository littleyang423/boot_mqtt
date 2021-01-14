package com.yhf.util;

import com.yhf.pojo.WeatherStation;

/**
 * @author yhf
 * @date -
 */
public interface WeatherStationUtil {
    public  byte[] getBytes();
    public WeatherStation setBytes(byte[] byt);
    public  WeatherStation analysis();
}
