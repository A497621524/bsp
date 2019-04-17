package com.redescooter.ecu.bsp.api.model;

public class BleScanMessage {

    private String uuid;
    private int rssi;

    public BleScanMessage(String uuid, int rssi) {
        this.uuid = uuid;
        this.rssi = rssi;
    }
}
