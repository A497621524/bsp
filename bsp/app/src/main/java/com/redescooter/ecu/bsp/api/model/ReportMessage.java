package com.redescooter.ecu.bsp.api.model;

// TODO: 2019/4/16
public class ReportMessage {
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
    private Gps Gps;

    
   
}
