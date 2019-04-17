package com.redescooter.ecu.bsp.api.listener;

/**
 * 用于RFID绑定
 */
public interface RfidBindingListener extends AbsListener{
    /**
     *
     * @param Rfid
     * @param key
     */
    void handle(String Rfid,String key);


}
