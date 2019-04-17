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



}
