package com.redescooter.ecu.bsp.api.config;

import com.redescooter.ecu.bsp.api.request.Request;

public class Config {

    private String batteryNum;//电池仓
    private boolean enable;//是否启用



    public static boolean start(String batteryNum){
        if (Request.Request(batteryNum)) {//注册成功
            return true;
        }else {
            return false;
        }
    }

    public static boolean close(String batteryNum){
        if (Request.Request(batteryNum)) {//注册成功
            return true;
        }else {
            return false;
        }
    }

}
