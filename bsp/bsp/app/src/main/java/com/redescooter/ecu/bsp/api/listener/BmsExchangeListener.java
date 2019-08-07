package com.redescooter.ecu.bsp.api.listener;

import com.redescooter.ecu.bsp.api.model.BmsExchangeMessage;

/**
 * 3	BMS监控数据
 * 3.1	BMS数据变动上报
 *
 * BMS数据有变动的时候请求应用层
 *
 */
public interface BmsExchangeListener extends AbsListener{

    void handle(BmsExchangeMessage msg);

}
