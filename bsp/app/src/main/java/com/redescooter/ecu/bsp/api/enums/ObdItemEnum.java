package com.redescooter.ecu.bsp.api.enums;

public enum ObdItemEnum {
    ALL(0,"所有"),MCU(1,"控制器"),ELECTRICAL_MACHINERY(2,"电机");

    private int id;
    private String name;


    ObdItemEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
