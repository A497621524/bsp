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

    public void register(AbsListener listener){
        this.listener = listener;
    }

    public void unregiste(AbsListener listener){
        this.listener = null;
    }




    public void bluetoothMatchingListener(){
        BluetoothMatchingListener bluetoothMatchingListener = (BluetoothMatchingListener)listener;
        List<BleScanMessage> uuid= new ArrayList();
        uuid.add(0,new BleScanMessage("3876543",80));
        uuid.add(1,new BleScanMessage("3546765",50));
        bluetoothMatchingListener.handle(uuid);
    }

    public void bmsExchangeListener(){
        BmsExchangeListener bmsExchangeListener = (BmsExchangeListener)listener;
        BmsExchangeMessage msg = new BmsExchangeMessage();
        bmsExchangeListener.handle(msg);
    }

    public void eventListener(){
        EventListener EventListener = (EventListener)listener;
        EventListener.handle("RFID","开锁");
    }

    public void faultReportListener(){
        FaultReportListener faultReportListener = (FaultReportListener)listener;
        ObdMessage msg = new ObdMessage();
        faultReportListener.handle(msg);
    }

    public void meterListener(){
        MeterListener meterListener = (MeterListener)listener;
        MeterMessage msg = new MeterMessage();
        meterListener.handle(msg);
    }

    public void rfidBindingListener(){
        RfidBindingListener rfidBindingListener = (RfidBindingListener)listener;
        rfidBindingListener.handle("53456342","312432");
    }

    public void rfidOperationListener(){
        RfidOperationListener rfidOperationListener = (RfidOperationListener)listener;
        rfidOperationListener.handle("345342","0987654");
    }

    
    public void timerReportListener(){
        TimerReportListener timerReportListener = (TimerReportListener)listener;
        ReportMessage msg = new ReportMessage();
        timerReportListener.handle(msg);
    }



}
