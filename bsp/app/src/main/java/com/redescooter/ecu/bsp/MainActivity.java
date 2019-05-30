package com.redescooter.ecu.bsp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android_serialport_api.SerialPort;

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

    private Toast toast;
    private Thread receiveThread;
    private Thread sendThread;
    private SerialPort mSerialPort;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {                  //接收
                String data = (String)msg.obj;
                if (data != null) {
//                    receiveinfo += "\n" + data;
//                    receivedata.setText(receiveinfo);
                    Log.e(TAG, "接受的数据" + data);
                    showTip(data);
                }
            }
            if (msg.what == 2){                   //发送
                String data = (String)msg.obj;
//                showTip("发送成功");
//                sendArea.setText("");
//                if (data != null) {
//                    sendinfo += "\n" + data;
//                    senddata.setText(sendinfo);
//                }
                Log.e(TAG, "发送的数据" + data);
            }
            if (msg.what == 3){
                showTip("发送失败");
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast = Toast.makeText(this, "启动成功", Toast.LENGTH_SHORT);

        Listener();
        Device();

        openSerial();
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

    private void openSerial() {
// 打开
        try {
            mSerialPort = new SerialPort(new File("/dev/ttyMT0"), 115200, 0);
            mInputStream = mSerialPort.getInputStream();
            mOutputStream = mSerialPort.getOutputStream();
            receiveThread();
            showTip("串口0打开成功");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            showTip("串口0打开失败");
            e.printStackTrace();
        }
    }

    /**
     * 开启串口
     */
    private void receiveThread() {
        // 接收
        receiveThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int size;
                    try {
                        byte[] buffer = new byte[1024];
                        if (mInputStream == null)
                            return;
                        size = mInputStream.read(buffer);
                        if (size > 0) {
                            String recinfo = new String(buffer, 0,
                                    size);
                            Log.i("TAG", "接收到串口信息:" + recinfo);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = recinfo;
                            handler.sendMessage(msg);
//                            sb = recinfo;
//                            handler.sendEmptyMessage(1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        receiveThread.start();
    }

    /**
     * 关闭串口
     */
    public void closeSerialPort() {

        if (mSerialPort != null) {
            mSerialPort.close();
        }
        if (mInputStream != null) {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mOutputStream != null) {
            try {
                mOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void showTip(final String str) {
        toast.setText(str);
        toast.show();
    }

}
