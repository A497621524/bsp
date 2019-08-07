package com.redescooter.ecu.bsp.api.listener;

/**
 * 2	异步通知接口
 * 2.1	刹车通知
 * 2.2	松刹车通知
 * 2.3	车辆停止状态下震动通知
 * 2.4	启动引擎通知
 * 2.5	关闭引擎通知
 * 2.6	转向灯通知
 * 2.7	转向灯复位通知
 * 2.8	打开后备箱通知
 * 2.9	关闭后备箱通知
 * 2.10	打开电池仓通知
 * 2.11	关闭电池仓通知
 * 2.12	拿出电池
 * 2.13	放进电池
 * 2.14	倾斜一定角度上报
 */
public interface EventListener extends AbsListener{

    void handle(String from,String event);

}
