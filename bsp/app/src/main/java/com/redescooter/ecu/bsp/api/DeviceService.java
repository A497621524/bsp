package com.redescooter.ecu.bsp.api;


import com.redescooter.ecu.bsp.api.enums.ObdItemEnum;
import com.redescooter.ecu.bsp.api.model.Bms;
import com.redescooter.ecu.bsp.api.model.Ecu;
import com.redescooter.ecu.bsp.api.model.Mcu;
import com.redescooter.ecu.bsp.exception.DeviceServiceException;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;
import com.redescooter.ecu.bsp.api.listener.RfidBindingListener;
import com.redescooter.ecu.bsp.api.model.RfidMessage;

import java.util.List;

/*
    所有通信超时时间为500ms
    所有接口不做重试，一次成功失败


 */
public interface DeviceService {

    public int OpenLock();

    public int CloseLock();

    public int OpenTrunkLock();

    public int CloseTrunkLock();
    /*
        获取BMS出厂信息
     */
    public Bms getBMS() throws DeviceServiceException;
    /*
        获取中央控制器出厂信息
     */
    public Ecu getECU() throws DeviceServiceException;
    /*
        获取控制器出厂信息
     */
    public Mcu getMCU() throws DeviceServiceException;

    public List<String> setBatteryIds(List<String> batteryIds) throws DeviceServiceException;

    public List<String> getBatteryIds() throws DeviceServiceException;
    /*
    主动获取定时上报数据
     */
    public ReportMessage getReport() throws DeviceServiceException;

    public ObdMessage getOBD(ObdItemEnum obdItemEnum) throws DeviceServiceException;

    /**
     *
     * @param msg 信息
     * @param rfidBindingListener
     * @param timeout 超时时间  单位ms
     * @return
     */
    public boolean bindingRfid(RfidMessage msg, RfidBindingListener rfidBindingListener,int timeout);



    }
