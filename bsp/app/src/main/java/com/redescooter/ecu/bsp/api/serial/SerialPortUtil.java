package com.redescooter.ecu.bsp.api.serial;

import android.databinding.adapters.ListenerUtil;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.redescooter.ecu.bsp.api.ListenerManager;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android_serialport_api.SerialPort;

public class SerialPortUtil {
    private final String TAG = "SerialPortUtil";
    private String path = "/dev/ttyMT0";
    private int baudrate = 115200;

    private Thread receiveThread;
    private Thread sendThread;
    private SerialPort mSerialPort;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    private DataBean dataBean;

    private static SerialPortUtil serialPortUtil;

    private ListenerManager listenerManager = new ListenerManager();//接口管理   使用接口线new 一个

    public Bms bms = new Bms();
    public Ecu ecu = new Ecu();
    public Mcu mcu = new Mcu();
    public MeterMessage meterMessage = new MeterMessage();
    public ReportMessage reportMessage = new ReportMessage();
    public int code;

    private void initListener() {
        listenerManager.registerBluetoothMatching(new BluetoothMatchingListener() {
            @Override
            public int handle(List<BleScanMessage> uuid) {
                Log.i(TAG, "handle: " + uuid.toString());
                return 0;
            }
        });
        listenerManager.registerBmsExchange(new BmsExchangeListener() {
            @Override
            public void handle(BmsExchangeMessage msg) {
                Log.i(TAG, "handle: " + msg.hashCode());

            }
        });
        listenerManager.registerEvent(new EventListener() {
            @Override
            public void handle(String from, String event) {
                Log.i(TAG, "from=" + from + " event=" + event);

            }
        });
        listenerManager.registerFaultReport(new FaultReportListener() {
            @Override
            public void handle(ObdMessage msg) {
                Log.i(TAG, "handle: " + msg.hashCode());

            }
        });
        listenerManager.registerRfidOperation(new RfidOperationListener() {
            @Override
            public boolean handle(String rfid, String key) {
                Log.i(TAG, "Rfid=" + rfid + " key=" + key);
                return false;
            }
        });
        listenerManager.registerRfidBinding(new RfidBindingListener() {
            @Override
            public void handle(String rfid, String key) {
                Log.i(TAG, "Rfid=" + rfid + " key=" + key);
            }
        });
        listenerManager.registerTimerReport(new TimerReportListener() {
            @Override
            public void handle(ReportMessage msg) {
                Log.i(TAG, "handle: " + msg.hashCode());

            }
        });
        listenerManager.registerMeter(new MeterListener() {
            @Override
            public void handle(MeterMessage msg) {
                Log.e(TAG, "MeterMessage: " + msg.toString());
            }
        });
    }

    public Handler serialHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String data = (String)msg.obj;
                    Log.e(TAG, "data： " + data);
                    Gson gson = new Gson();
                    dataBean = gson.fromJson(data,DataBean.class);

                    if (dataBean.getAction().equals("meter")){
                        serialHandler.sendEmptyMessage(20);
                    }
                    if (dataBean.getAction().equals("timerData")){
                        serialHandler.sendEmptyMessage(21);
                    }
                    if (dataBean.getAction().equals("notice")){
                        serialHandler.sendEmptyMessage(22);
                    }
                    if (dataBean.getAction().equals("bms")){
                        serialHandler.sendEmptyMessage(23);
                    }
                    break;
                case 20://仪表信息
                    code = dataBean.getCode();
                    meterMessage.setAmbientTemperature(dataBean.getdata().getAmbientTemperature());
                    meterMessage.setBattery(dataBean.getdata().getBattery());
                    meterMessage.setClimbingAngle(dataBean.getdata().getClimbingAngle());
                    meterMessage.setSingleMileage(dataBean.getdata().getSingleMileage());
                    meterMessage.setSpeed(dataBean.getdata().getSpeed());
                    meterMessage.setTorque(dataBean.getdata().getTorque());
                    meterMessage.setTotalMileage(dataBean.getdata().getTotalMileage());
                    listenerManager.meterListener(meterMessage);
                    Log.e(TAG, "仪表数据: " + meterMessage.toString());
                    break;
                case 21://定时上报
                    reportMessage.setBatteryCompartmentLockStatus(dataBean.getdata().getBatteryCompartmentLockStatus());
                    reportMessage.setBatteryTemperature(dataBean.getdata().getBatteryTemperature());
                    reportMessage.setCapacity(dataBean.getdata().getCapacity());
                    reportMessage.setClimbingAngle(dataBean.getdata().getClimbingAngle());
                    reportMessage.setCurrent(dataBean.getdata().getCurrent());
                    reportMessage.setExternalTemperature(dataBean.getdata().getExternalTemperature());
                    reportMessage.getGps().setGPGGA(dataBean.getdata().getGps().getGPGGA());
                    reportMessage.getGps().setGPGLL(dataBean.getdata().getGps().getGPGLL());
                    reportMessage.getGps().setGPGSA(dataBean.getdata().getGps().getGPGSA());
                    reportMessage.getGps().setGPGSV(dataBean.getdata().getGps().getGPGSV());
                    reportMessage.getGps().setGPRMC(dataBean.getdata().getGps().getGPRMC());
                    reportMessage.setInclinationAngle(dataBean.getdata().getTotalMileage());
                    reportMessage.setLockStatus(dataBean.getdata().getLockStatus());
                    reportMessage.setMotorSpeed(dataBean.getdata().getMotorSpeed());
                    reportMessage.setSingleMileage(dataBean.getdata().getSingleMileage());
                    reportMessage.setSpeed(dataBean.getdata().getSpeed());
                    reportMessage.setTorsion(dataBean.getdata().getTotalMileage());
                    reportMessage.setTrunkLockStatus(dataBean.getdata().getTrunkLockStatus());
                    reportMessage.setTrunkTemperature(dataBean.getdata().getTrunkTemperature());
                    reportMessage.setVoltage(dataBean.getdata().getVoltage());
                    listenerManager.timerReportListener(reportMessage);
                    Log.e(TAG, "定时上报数据: " + reportMessage.toString());
                    break;
                case 22://notice

                    break;
                case 23://bms
                    bms.setBatteryIds(dataBean.getdata().getBatteryIds());
                    break;
            }
        }
    };


    /**
     * 打开串口
     */
    private void openSerial() {
        initListener();//注册接口
        try {
            mSerialPort = new SerialPort(new File(path), baudrate, 0);
            mInputStream = mSerialPort.getInputStream();
            mOutputStream = mSerialPort.getOutputStream();
            receiveThread();
            Log.e(TAG,"串口0打开成功");
        } catch (SecurityException e) {
            e.printStackTrace();
            Log.e(TAG, "打开串口0异常：" + e.toString());

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "打开串口0异常：" + e.toString());

        }



    }

    /**
     * 接收串口数据
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
                            int j = 0;
                            for (int i = 0;i < buffer.length;i++){
                                if (buffer[i + 1] != 0){
                                    j++;
                                }else {
                                    break;
                                }
                            }
                            byte[] bytes = new byte[j];
                            for (int i = 0;i < j;i++){
                                bytes[i] = buffer[i];
                            }
                            String recinfo = new String(bytes,"GBK");
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = recinfo;
                            serialHandler.sendMessage(msg);
//                            handler.sendMessage(msg);
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
     * 发送串口指令（字符串）
     * @param data String数据指令
     */
    public void sendSerialPort(final String data){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.e(TAG, "sendSerialPort: 发送数据: " + Arrays.toString(data.getBytes()));
                try {
                    if (data.length() > 0) {
                        mOutputStream.write(data.getBytes());
                        mOutputStream.flush();
                        Log.e(TAG, "sendSerialPort: 串口数据发送成功: " + data);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "sendSerialPort: 串口数据发送失败："+e.toString());
                }
            }
        }.start();


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

    public static SerialPortUtil getSerialPortUtil(){
        if (serialPortUtil == null) {
            serialPortUtil = new SerialPortUtil();
            serialPortUtil.openSerial();
        }
        return serialPortUtil;
    }





}



