package com.redescooter.ecu.bsp.api;

import com.redescooter.ecu.bsp.api.listener.AbsListener;
import com.redescooter.ecu.bsp.api.listener.BluetoothMatchingListener;
import com.redescooter.ecu.bsp.api.listener.BmsExchangeListener;
import com.redescooter.ecu.bsp.api.listener.FaultReportListener;
import com.redescooter.ecu.bsp.api.listener.MeterListener;
import com.redescooter.ecu.bsp.api.listener.EventListener;
import com.redescooter.ecu.bsp.api.listener.RfidOperationListener;
import com.redescooter.ecu.bsp.api.listener.TimerReportListener;
import com.redescooter.ecu.bsp.api.model.BleScanMessage;
import com.redescooter.ecu.bsp.api.model.BmsExchangeMessage;
import com.redescooter.ecu.bsp.api.model.MeterMessage;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;

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
    private static RfidOperationListener rfidOperationListener;
    private static TimerReportListener timerReportListener;

    private volatile static ListenerManager listenerManager = null;


    public ListenerManager() {
    }

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
    public void unregisteRfidOperation(AbsListener listener){ this.rfidOperationListener = null; }
    public void unregisteTimerReport(AbsListener listener){ this.timerReportListener = null; }

    /**
     * 数据写入接口
     */
    public int bluetoothMatchingListener(List<BleScanMessage> uuid){
        return bluetoothMatchingListener.handle(uuid);
    }

    public void bmsExchangeListener(BmsExchangeMessage msg){
        bmsExchangeListener.handle(msg);
    }

    public void eventListener(String from,String event){
        eventListener.handle(from,event);
    }

    public void faultReportListener(ObdMessage msg){
        faultReportListener.handle(msg);
    }

    public void meterListener(MeterMessage msg){
        meterListener.handle(msg);
    }


    public boolean rfidOperationListener(String rfid, String key){
        return rfidOperationListener.handle(rfid,key);
    }

    public void timerReportListener(ReportMessage msg){
        timerReportListener.handle(msg);
    }

    public static ListenerManager getListenerManager() {
        if (listenerManager == null) {
            synchronized (ListenerManager.class) {
                if (listenerManager == null) {
                    listenerManager = new ListenerManager();
                }
            }
        }
        return listenerManager;
    }

}
