package com.redescooter.ecu.bsp.api.listener;

/**
 * 用于RFID操作
 */
public interface RfidOperationListener extends AbsListener{
    /**
     * 开关锁的回调通知
     * @param rfid
     * @param key
     * @return
     */
    boolean handle(String rfid,String key);

}
