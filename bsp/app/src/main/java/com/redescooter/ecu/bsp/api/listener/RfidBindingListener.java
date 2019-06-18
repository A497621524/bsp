package com.redescooter.ecu.bsp.api.listener;

/**
 * 用于RFID绑定
 */
public interface RfidBindingListener extends AbsListener{
    /**
     *
     * @param rfid
     * @param key
     */
    void handle(String rfid,String key);


}
