package com.redescooter.ecu.bsp;

import android.os.DeadObjectException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.redescooter.ecu.bsp.api.DeviceService;
import com.redescooter.ecu.bsp.api.DeviceServiceTool;
import com.redescooter.ecu.bsp.api.ListenerManager;
import com.redescooter.ecu.bsp.api.R;
import com.redescooter.ecu.bsp.api.enums.ObdItemEnum;
import com.redescooter.ecu.bsp.api.listener.BluetoothMatchingListener;
import com.redescooter.ecu.bsp.api.listener.BmsExchangeListener;
import com.redescooter.ecu.bsp.api.listener.EventListener;
import com.redescooter.ecu.bsp.api.listener.FaultReportListener;
import com.redescooter.ecu.bsp.api.listener.MeterListener;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListenerManager BluetoothMatchingListener = new ListenerManager();
    private ListenerManager BmsExchangeListener = new ListenerManager();
    private ListenerManager EventListener = new ListenerManager();
    private ListenerManager FaultReportListener = new ListenerManager();
    private ListenerManager MeterListener = new ListenerManager();
    private ListenerManager RfidBindingListener = new ListenerManager();
    private ListenerManager RfidOperationListener = new ListenerManager();
    private ListenerManager TimerReportListener = new ListenerManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Listener();
        Device();
    }

    private void Device(){
        DeviceService deviceService = new DeviceServiceTool();
        int results1 = deviceService.openLock();
        int results2 = deviceService.closeLock();
        int results3 = deviceService.openTrunkLock();
        int results4 = deviceService.closeTrunkLock();

        try {
            Bms bms = deviceService.getBMS();
            Ecu ecu = deviceService.getECU();
            Mcu mcu = deviceService.getMCU();

            List<String> list1 = new ArrayList<String>();
            list1.add("435132342");
            list1.add("987345233");
            List<String> list2 = deviceService.setBatteryIds(list1);

            list2 = deviceService.getBatteryIds();

            ReportMessage reportMessage = deviceService.getReport();

            ObdMessage obdMessage = deviceService.getOBD(ObdItemEnum.ALL);

            boolean results5 = deviceService.bindingRfid(new RfidMessage(), new RfidBindingListener() {
                @Override
                public void handle(String rfid, String key) {

                }
            },5000);

        } catch (DeviceServiceException e) {
            e.printStackTrace();
        }

    }

    public void Listener(){
        BluetoothMatchingListener.register(new BluetoothMatchingListener() {
            @Override
            public int handle(List<BleScanMessage> uuid) {
                Log.i(TAG, "handle: " + uuid.toString());
                return 0;
            }
        });

        BmsExchangeListener.register(new BmsExchangeListener() {
            @Override
            public void handle(BmsExchangeMessage msg) {
                Log.i(TAG, "handle: " + msg.hashCode());
            }
        });

        EventListener.register(new EventListener() {

            @Override
            public void handle(String from, String event) {
                Log.i(TAG, "from=" + from + " event=" + event);
            }
        });

        FaultReportListener.register(new FaultReportListener() {

            @Override
            public void handle(ObdMessage msg) {
                Log.i(TAG, "handle: " + msg.hashCode());
            }
        });

        MeterListener.register(new MeterListener() {
            @Override
            public void handle(MeterMessage msg) {
                Log.i(TAG, "handle: " + msg.hashCode());
            }

        });

        RfidBindingListener.register(new RfidBindingListener() {
            @Override
            public void handle(String rfid, String key) {
                Log.i(TAG, "Rfid=" + rfid + " key=" + key);
            }
        });

        RfidOperationListener.register(new RfidOperationListener() {
            @Override
            public boolean handle(String rfid, String key) {
                Log.i(TAG, "Rfid=" + rfid + " key=" + key);
                return false;
            }
        });

        TimerReportListener.register(new TimerReportListener() {
            @Override
            public void handle(ReportMessage msg) {
                Log.i(TAG, "handle: " + msg.hashCode());
            }
        });
    }

    public void onClick(View v){
            switch (v.getId()){
                case R.id.BluetoothMatchingListener:
                    BluetoothMatchingListener.bluetoothMatchingListener();
                    break;
                case R.id.BmsExchangeListener:
                    BmsExchangeListener.bmsExchangeListener();
                    break;
                case R.id.EventListener:
                    EventListener.eventListener();
                    break;
                case R.id.FaultReportListener:
                    FaultReportListener.faultReportListener();
                    break;
                case R.id.MeterListener:
                    MeterListener.meterListener();
                    break;
                case R.id.RfidBindingListener:
                    RfidBindingListener.rfidBindingListener();
                    break;
                case R.id.RfidOperationListener:
                    RfidOperationListener.rfidOperationListener();
                    break;
                case R.id.TimerReportListener:
                    TimerReportListener.timerReportListener();
                    break;
            }
    }

}
