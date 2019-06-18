package com.redescooter.ecu.bsp.api.model;

public class MeterMessage {
    /**
     * 速度   单位m/s
     */
    private float speed;
    /**
     * 电量百分比
     */
    private float battery;
    /**
     * 总里程  单位 米
     */
    private float totalMileage;


    /**
     * 单次里程  单位 米
     * 从开锁到关锁为一次里程
     */
    private float singleMileage;
    /**
     * 环境温度  单位 摄氏度
     */
    private float ambientTemperature ;

    /**
     * 扭力       单位 牛顿
     */
    private float torque;
    /**
     * 爬坡角度   单位 度
     */
    private float climbingAngle ;


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getBattery() {
        return battery;
    }

    public void setBattery(float battery) {
        this.battery = battery;
    }

    public float getTotalMileage() {
        return totalMileage;
    }

    public void setTotalMileage(float totalMileage) {
        this.totalMileage = totalMileage;
    }

    public float getSingleMileage() {
        return singleMileage;
    }

    public void setSingleMileage(float singleMileage) {
        this.singleMileage = singleMileage;
    }

    public float getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(float ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(float torque) {
        this.torque = torque;
    }

    public float getClimbingAngle() {
        return climbingAngle;
    }

    public void setClimbingAngle(float climbingAngle) {
        this.climbingAngle = climbingAngle;
    }

    @Override
    public String toString() {
        return "MeterMessage{" +
                "speed=" + speed +
                ", battery=" + battery +
                ", totalMileage=" + totalMileage +
                ", singleMileage=" + singleMileage +
                ", ambientTemperature=" + ambientTemperature +
                ", torque=" + torque +
                ", climbingAngle=" + climbingAngle +
                '}';
    }
}
