package com.redescooter.ecu.bsp.api;

import com.redescooter.ecu.bsp.api.enums.ObdItemEnum;
import com.redescooter.ecu.bsp.api.listener.RfidBindingListener;
import com.redescooter.ecu.bsp.api.model.Bms;
import com.redescooter.ecu.bsp.api.model.Ecu;
import com.redescooter.ecu.bsp.api.model.Mcu;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;
import com.redescooter.ecu.bsp.api.model.RfidMessage;
import com.redescooter.ecu.bsp.api.serial.SerialPortUtil;
import com.redescooter.ecu.bsp.exception.DeviceServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回1为正常，负数为异常,异常未定
 */
public class DeviceServiceTool implements DeviceService{

    private SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();

    @Override
    public int openLock() {
        serialPortUtil.sendSerialPort("{\"action\":\"openLock\", \"data\":{ \"code\":\"1\"}");
        if (serialPortUtil.code == 1) {
            int code = serialPortUtil.code;
            serialPortUtil.code = 0;
            return code;
        }
        return 1;
    }

    @Override
    public int closeLock() {
        serialPortUtil.sendSerialPort("{\"action\":\"closeLock\", \"data\":{ \"code\":\"1\"}");
        if (serialPortUtil.code == 1) {
            int code = serialPortUtil.code;
            serialPortUtil.code = 0;
            return code;
        }
        return 1;
    }

    @Override
    public int openTrunkLock() {
        serialPortUtil.sendSerialPort("{\"action\":\"openTrunkLock\", \"data\":{ \"code\":\"1\"}");
        if (serialPortUtil.code == 1) {
            int code = serialPortUtil.code;
            serialPortUtil.code = 0;
            return code;
        }
        return 1;
    }

    @Override
    public int closeTrunkLock() {
        serialPortUtil.sendSerialPort("{\"action\":\"closeTrunkLock\", \"data\":{ \"code\":\"1\"}");
        if (serialPortUtil.code == 1) {
            int code = serialPortUtil.code;
            serialPortUtil.code = 0;
            return code;
        }
        return 1;
    }

    @Override
    public Bms getBMS() throws DeviceServiceException {
        serialPortUtil.sendSerialPort("{\"action\":\"getBMS\", \"data\":{ \"code\":\"1\"}");
        if (serialPortUtil.bms != null) {
            Bms bms = serialPortUtil.bms;
            serialPortUtil.bms = null;
            return bms;
        }else {
            return null;//如果读不到数据返回null
        }
    }




    @Override
    public Ecu getECU() throws DeviceServiceException {
        serialPortUtil.sendSerialPort("{\"action\":\"getBMS\", \"data\":{ \"code\":\"1\"}");
        if (serialPortUtil.ecu != null) {
            Ecu ecu = serialPortUtil.ecu;
            serialPortUtil.ecu = null;
            return ecu;
        }else {
            return null;//如果读不到数据返回null
        }
    }

    @Override
    public Mcu getMCU() throws DeviceServiceException {
        serialPortUtil.sendSerialPort("{\"action\":\"getBMS\", \"data\":{ \"code\":\"1\"}");
        if (serialPortUtil.mcu != null) {
            Mcu mcu = serialPortUtil.mcu;
            serialPortUtil.mcu = null;
            return mcu;
        }else {
            return null;//如果读不到数据返回null
        }
    }

    @Override
    public List<String> setBatteryIds(List<String> batteryIds) throws DeviceServiceException {
        //xxxx.xxx(batteryIds)
        List<String> list = new ArrayList<String>();
        list.add("3245231234");
        list.add("9123438356");
        return list;
    }

    @Override
    public List<String> getBatteryIds() throws DeviceServiceException {
        List<String> list = new ArrayList<String>();
        list.add("3245231234");
        list.add("9123438356");
        return list;
    }

    @Override
    public ReportMessage getReport() throws DeviceServiceException {
        ReportMessage reportMessage = new ReportMessage();
        return reportMessage;
    }

    @Override
    public ObdMessage getOBD(ObdItemEnum obdItemEnum) throws DeviceServiceException {
        ObdMessage obdMessage = new ObdMessage();
        return obdMessage;
    }

    @Override
    public boolean bindingRfid(RfidMessage msg, RfidBindingListener rfidBindingListener, int timeout) {
        String Rfid = msg.getId();
        String key = msg.getKey();
        int timeout1 = timeout;

        return true;
    }

}
