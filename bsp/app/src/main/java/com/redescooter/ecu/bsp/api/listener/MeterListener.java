package com.redescooter.ecu.bsp.api.listener;

import com.redescooter.ecu.bsp.api.model.MeterMessage;

/**
 *  仪表主数据 更新时间帧数 5ms
 *
 * 9	仪表数据
 * 9.1	速度
 * 9.2	电量
 * 9.3	总里程
 * 9.4	单次里程
 * 9.5	环境温度
 * 9.6	时间
 * 9.7	扭力
 * 9.8	爬坡角度
 *
 */
public interface MeterListener extends AbsListener{

    public void handle(MeterMessage msg);

}
