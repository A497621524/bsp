package com.redescooter.ecu.bsp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
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
import com.redescooter.ecu.bsp.api.serial.DataBean;
import com.redescooter.ecu.bsp.api.serial.SerialPortUtil;
import com.redescooter.ecu.bsp.exception.DeviceServiceException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android_serialport_api.SerialPort;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toast toast;
    private SerialPortUtil serialPortUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast = Toast.makeText(this, "启动成功", Toast.LENGTH_SHORT);
        serialPortUtil = SerialPortUtil.getSerialPortUtil(); //获得串口收发的对象

//        Device();//测试用
        ListenerManager listenerManager = new ListenerManager(); //测试串口收到数据后能后推过来
        listenerManager.registerMeter(new MeterListener() {
            @Override
            public void handle(MeterMessage msg) {
                Log.e(TAG, "MainMeterMessage" + msg.toString());
            }
        });
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

    public void test(View v){
        new Thread(){
            @Override
            public void run() {
                super.run();
                serialPortUtil.sendSerialPort("串口"); //测试发送
            }
        }.start();
    }



    /**
     * 信息提示
     * @param str 弹出的信息
     */
    private void showTip(final String str) {
        toast.setText(str);
        toast.show();
    }


}
