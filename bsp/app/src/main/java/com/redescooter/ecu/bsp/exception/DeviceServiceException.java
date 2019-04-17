package com.redescooter.ecu.bsp.exception;

import android.os.Message;

public class DeviceServiceException extends Exception {

    private String Code;
    private String Msg;


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
