package com.redescooter.ecu.bsp.api;


import com.redescooter.ecu.bsp.api.enums.ObdItemEnum;
import com.redescooter.ecu.bsp.api.model.Bms;
import com.redescooter.ecu.bsp.api.model.Ecu;
import com.redescooter.ecu.bsp.api.model.Mcu;
import com.redescooter.ecu.bsp.exception.DeviceServiceException;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;
import com.redescooter.ecu.bsp.api.model.RfidMessage;

import java.util.List;

/**
 * 所有通信超时时间为500ms
 * 所有接口不做重试，一次成功失败
 */
public interface DeviceService {

    int openLock() throws DeviceServiceException;

    int closeLock() throws DeviceServiceException;

    int openTrunkLock() throws DeviceServiceException;

    int closeTrunkLock() throws DeviceServiceException;
    /*
        获取BMS出厂信息
     */
    Bms getBMS() throws DeviceServiceException;
    /*
        获取中央控制器出厂信息
     */
    Ecu getECU() throws DeviceServiceException;
    /*
        获取控制器出厂信息
     */
    Mcu getMCU() throws DeviceServiceException;

    /**
     *  设置电池组id
     * @param batteryIds 电池组id
     * @return 电池组id
     * @throws DeviceServiceException
     */
    List<String> setBatteryIds(List<String> batteryIds) throws DeviceServiceException;

    /**
     * 获取电池组id
     * @return 电池组id
     * @throws DeviceServiceException
     */
    List<String> getBatteryIds() throws DeviceServiceException;
    /*
    主动获取定时上报数据
     */
    ReportMessage getReport() throws DeviceServiceException;

    /**
     *  获取OBD信息
     * @param obdItemEnum 获取指定的OBD
     * @return OBD信息
     * @throws DeviceServiceException
     */
    ObdMessage getOBD(ObdItemEnum obdItemEnum) throws DeviceServiceException;


    }
