package com.redescooter.ecu.bsp.api.listener;

import com.redescooter.ecu.bsp.api.model.ReportMessage;

/**
 * 4	定时上报数据（暂定3秒，时间可调整）
 * 4.1	车锁状态
 * 4.2	后备箱锁状态
 * 4.3	后备箱温度
 * 4.4	电池仓锁状态
 * 4.5	速度
 * 4.6	定位
 * 4.7	倾斜角度
 * 4.8	电流
 * 4.9	电压
 * 4.10	电量
 * 4.11	扭力
 * 4.12	电池温度
 * 4.13	车外温度
 * 4.14	电机转速
 * 4.15	单次里程（开关锁为一次里程）
 * 4.16	爬坡角度
 *
 */
public interface TimerReportListener extends AbsListener{

    void handle(ReportMessage msg);

}
