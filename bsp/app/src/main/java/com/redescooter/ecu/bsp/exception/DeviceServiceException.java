package com.redescooter.ecu.bsp.exception;

import android.os.Message;

import java.io.IOException;

/**
 * 暂定：
 *      1.与单片机连接超时
 *      2.
 */
public class DeviceServiceException extends IOException {

    private static final long serialVersionUID = 7818375828146090155L;

    private String Code;
    private String Msg;

    public DeviceServiceException(){

    }

    public DeviceServiceException(String Code,String Msg){
        super("\nCode: " + Code + ",Msg: " + Msg);
        this.Code = Code;
        this.Msg = Msg;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
