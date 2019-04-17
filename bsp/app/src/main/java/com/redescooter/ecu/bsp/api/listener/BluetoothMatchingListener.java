package com.redescooter.ecu.bsp.api.listener;


import com.redescooter.ecu.bsp.api.model.BleScanMessage;

import java.util.List;

/**
 *
 * 7	蓝牙相关
 * 7.1	手机蓝牙匹配
 * 7.2	5米外自动关锁
 * 7.3	5米内自动开锁
 *
 *      将5米内扫描到的蓝牙信息全部发送给应用层
 *      应用层返回 1代表有，0代表没有
 *
 *
 *      关闭状态下
 *          请求应用层并返回1则开锁
 *      开启状态下
 *          车辆静止且引擎关闭状态且请求应用层返回0则关锁
 *
 */
public interface BluetoothMatchingListener extends AbsListener{

    int handle(List<BleScanMessage> uuid);


}
