package com.redescooter.ecu.bsp.api;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.redescooter.ecu.bsp.api.model.BmsExchangeMessage;
import com.redescooter.ecu.bsp.api.model.MeterMessage;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;

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




    public void BluetoothMatchingListener(){
        BluetoothMatchingListener bluetoothMatchingListener = (BluetoothMatchingListener)listener;
        List<BleScanMessage> uuid= new ArrayList();
        uuid.add(0,new BleScanMessage("3876543",80));
        uuid.add(1,new BleScanMessage("3546765",50));
        bluetoothMatchingListener.handle(uuid);
    }

    public void BmsExchangeListener(){
        BmsExchangeListener bmsExchangeListener = (BmsExchangeListener)listener;
        BmsExchangeMessage msg = new BmsExchangeMessage();
        bmsExchangeListener.handle(msg);
    }

    public void EventListener(){
        EventListener EventListener = (EventListener)listener;
        EventListener.handle("RFID","开锁");
    }

    public void FaultReportListener(){
        FaultReportListener faultReportListener = (FaultReportListener)listener;
        ObdMessage msg = new ObdMessage();
        faultReportListener.handle(msg);
    }

    public void MeterListener(){
        MeterListener meterListener = (MeterListener)listener;
        MeterMessage msg = new MeterMessage();
        meterListener.handle(msg);
    }

    public void RfidBindingListener(){
        RfidBindingListener rfidBindingListener = (RfidBindingListener)listener;
        rfidBindingListener.handle("53456342","312432");
    }

    public void RfidOperationListener(){
        RfidOperationListener rfidOperationListener = (RfidOperationListener)listener;
        rfidOperationListener.handle("345342","0987654");
    }

    
    public void TimerReportListener(){
        TimerReportListener timerReportListener = (TimerReportListener)listener;
        ReportMessage msg = new ReportMessage();
        timerReportListener.handle(msg);
    }

}
