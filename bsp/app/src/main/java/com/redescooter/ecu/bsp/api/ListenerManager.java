package com.redescooter.ecu.bsp.api;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.redescooter.ecu.bsp.api.enums.ObdItemEnum;
import com.redescooter.ecu.bsp.api.listener.AbsListener;
import com.redescooter.ecu.bsp.api.listener.BluetoothMatchingListener;
import com.redescooter.ecu.bsp.api.listener.BmsExchangeListener;
import com.redescooter.ecu.bsp.api.listener.FaultReportListener;
import com.redescooter.ecu.bsp.api.listener.MeterListener;
import com.redescooter.ecu.bsp.api.listener.EventListener;
import com.redescooter.ecu.bsp.api.listener.RfidBindingListener;
import com.redescooter.ecu.bsp.api.listener.RfidOperationListener;
import com.redescooter.ecu.bsp.api.listener.TimerReportListener;
import com.redescooter.ecu.bsp.api.model.BleScanMessage;
import com.redescooter.ecu.bsp.api.model.Bms;
import com.redescooter.ecu.bsp.api.model.BmsExchangeMessage;
import com.redescooter.ecu.bsp.api.model.Ecu;
import com.redescooter.ecu.bsp.api.model.Mcu;
import com.redescooter.ecu.bsp.api.model.MeterMessage;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;
import com.redescooter.ecu.bsp.api.model.RfidMessage;
import com.redescooter.ecu.bsp.api.serial.SerialPortUtil;
import com.redescooter.ecu.bsp.exception.DeviceServiceException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有Listener只能注册一次
 * 如果重复注册后面的覆盖前面的
 */
public class ListenerManager {



    private AbsListener listener;
    private static BluetoothMatchingListener bluetoothMatchingListener;
    private static BmsExchangeListener bmsExchangeListener;
    private static EventListener eventListener;
    private static FaultReportListener faultReportListener;
    private static MeterListener meterListener;
    private static RfidBindingListener rfidBindingListener;
    private static RfidOperationListener rfidOperationListener;
    private static TimerReportListener timerReportListener;

    /***
     * 绑定对应的接口
     */
    public void registerAbs(AbsListener listener){
        this.listener = listener;
    }
    public void registerBluetoothMatching(BluetoothMatchingListener listener){
        this.bluetoothMatchingListener = listener;
    }
    public void registerBmsExchange(BmsExchangeListener listener){
        this.bmsExchangeListener = listener;
    }
    public void registerEvent(EventListener listener){
        this.eventListener = listener;
    }
    public void registerFaultReport(FaultReportListener listener){
        this.faultReportListener = listener;
    }
    public void registerMeter(MeterListener listener){
        this.meterListener = listener;
    }
    public void registerRfidBinding(RfidBindingListener listener){
        this.rfidBindingListener = listener;
    }
    public void registerRfidOperation(RfidOperationListener listener){
        this.rfidOperationListener = listener;
    }
    public void registerTimerReport(TimerReportListener listener){
        this.timerReportListener = listener;
    }

    /**
     * 解绑对应的接口
     */
    public void unregisteAbs(AbsListener listener){ this.listener = null; }
    public void unregisteBluetoothMatching(AbsListener listener){ this.bluetoothMatchingListener = null; }
    public void unregisteBmsExchange(AbsListener listener){ this.bmsExchangeListener = null; }
    public void unregisteEvent(AbsListener listener){ this.eventListener = null; }
    public void unregisteFaultReport(AbsListener listener){ this.faultReportListener = null; }
    public void unregisteMeter(AbsListener listener){ this.meterListener = null; }
    public void unregisteRfidBinding(AbsListener listener){ this.rfidBindingListener = null; }
    public void unregisteRfidOperation(AbsListener listener){ this.rfidOperationListener = null; }
    public void unregisteTimerReport(AbsListener listener){ this.timerReportListener = null; }

    /**
     * 数据写入接口
     */
    public void bluetoothMatchingListener(List<BleScanMessage> uuid){
        uuid.add(0,new BleScanMessage("3876543",80));
        uuid.add(1,new BleScanMessage("3546765",50));
        bluetoothMatchingListener.handle(uuid);
    }

    public void bmsExchangeListener(BmsExchangeMessage msg){
        bmsExchangeListener.handle(msg);
    }

    public void eventListener(){
        eventListener.handle("RFID","开锁");
    }

    public void faultReportListener(ObdMessage msg){
        faultReportListener.handle(msg);
    }

    public void meterListener(MeterMessage msg){
        meterListener.handle(msg);
    }

    public void rfidBindingListener(){
        rfidBindingListener.handle("53456342","312432");
    }

    public void rfidOperationListener(){
        rfidOperationListener.handle("345342","0987654");
    }

    public void timerReportListener(ReportMessage msg){
        timerReportListener.handle(msg);
    }



}
