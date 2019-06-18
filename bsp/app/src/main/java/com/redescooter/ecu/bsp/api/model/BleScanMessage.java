package com.redescooter.ecu.bsp.api.model;

public class BleScanMessage {

    /**
     * uuid
     */
    private String uuid;
    /**
     * 信号强度
     */
    private int rssi;

    public BleScanMessage(String uuid, int rssi) {
        this.uuid = uuid;
        this.rssi = rssi;
    }
}
