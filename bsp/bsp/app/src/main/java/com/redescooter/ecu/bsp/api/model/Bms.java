package com.redescooter.ecu.bsp.api.model;

import java.util.List;

public class Bms {

    private List<String> batteryIds;


    public List<String> getBatteryIds() {
        return batteryIds;
    }

    public void setBatteryIds(List<String> batteryIds) {
        this.batteryIds = batteryIds;
    }

}
