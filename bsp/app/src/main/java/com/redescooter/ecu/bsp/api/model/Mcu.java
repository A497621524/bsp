package com.redescooter.ecu.bsp.api.model;

public class Mcu {

    private String controllerVersionNumber;
    private String factoryVersion;
    private double motorSpeed;

    public String getControllerVersionNumber() {
        return controllerVersionNumber;
    }

    public void setControllerVersionNumber(String controllerVersionNumber) {
        this.controllerVersionNumber = controllerVersionNumber;
    }

    public String getFactoryVersion() {
        return factoryVersion;
    }

    public void setFactoryVersion(String factoryVersion) {
        this.factoryVersion = factoryVersion;
    }

    public double getMotorSpeed() {
        return motorSpeed;
    }

    public void setMotorSpeed(double motorSpeed) {
        this.motorSpeed = motorSpeed;
    }

}
