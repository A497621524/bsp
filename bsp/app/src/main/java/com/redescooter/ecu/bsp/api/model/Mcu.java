package com.redescooter.ecu.bsp.api.model;

public class Mcu {
    /**
     * 车锁状态
     */
    private boolean lockStatus;

    /**
     * 后备箱锁状态
     */
    private boolean trunkLockStatus ;

    /**
     * 后备箱温度   单位 摄氏度
     */
    private float trunkTemperature  ;

    /**
     * 电池仓锁状态
     */
    private boolean batteryCompartmentLockStatus;

    /**
     * 速度       单位 m/s
     */
    private float speed;

    /**
     * 定位
     */
    private Gps gps = new Gps();

    /**
     * 倾斜角度
     */
    private double inclinationAngle ;

    /**
     * 电流
     */
    private double current;

    /**
     * 电压
     */
    private double voltage;

    /**
     * 电量
     */
    private double capacity;

    /**
     * 扭力
     */
    private double torsion;

    /**
     * 电池温度
     */
    private double batteryTemperature;

    /**
     * 车外温度
     */
    private double externalTemperature;

    /**
     * 电机转速
     */
    private double motorSpeed;
    /**
     * 单次里程
     */
    private double singleMileage;
    /**
     * 爬坡角度
     */
    private double climbingAngle;

    /**
     * 档位
     */
    private int gears;

}
