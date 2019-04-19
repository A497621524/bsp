package com.redescooter.ecu.bsp.api;

import com.redescooter.ecu.bsp.api.enums.ObdItemEnum;
import com.redescooter.ecu.bsp.api.listener.RfidBindingListener;
import com.redescooter.ecu.bsp.api.model.Bms;
import com.redescooter.ecu.bsp.api.model.Ecu;
import com.redescooter.ecu.bsp.api.model.Mcu;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;
import com.redescooter.ecu.bsp.api.model.RfidMessage;
import com.redescooter.ecu.bsp.exception.DeviceServiceException;

import java.util.ArrayList;
import java.util.List;

public class DeviceServiceTool implements DeviceService{

    @Override
    public int openLock() {

        return 1;
    }

    @Override
    public int closeLock() {

        return 1;
    }

    @Override
    public int openTrunkLock() {

        return 1;
    }

    @Override
    public int closeTrunkLock() {

        return 1;
    }

    @Override
    public Bms getBMS() throws DeviceServiceException {
        Bms bms = new Bms();
        return bms;
    }

    @Override
    public Ecu getECU() throws DeviceServiceException {
        Ecu ecu = new Ecu();
        return ecu;
    }

    @Override
    public Mcu getMCU() throws DeviceServiceException {
        Mcu mcu = new Mcu();
        return mcu;
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
        return null;
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
