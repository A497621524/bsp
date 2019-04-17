package com.redescooter.ecu.bsp.api.listener;

/**
 * 6	OBD
 * 6.1	控制器相关
 * 6.2	电机相关
 * 6.3	仪表相关
 * 6.4	电池相关
 * 6.5	电池中端板相关
 * 6.6	刹车
 * 6.7	转把
 * 6.8	DC-DC
 * 6.9	电源锁
 * 6.10	空气开关
 *
 * 主动上报故障信息
 *
 */
public interface FaultReportListener extends AbsListener{

    void handle(FaultReportListener msg);

}
