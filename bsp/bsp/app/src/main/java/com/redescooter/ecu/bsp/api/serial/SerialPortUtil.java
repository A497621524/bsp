package com.redescooter.ecu.bsp.api.serial;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.redescooter.ecu.bsp.MainActivity;
import com.redescooter.ecu.bsp.api.DeviceServiceTool;
import com.redescooter.ecu.bsp.api.ListenerManager;
import com.redescooter.ecu.bsp.api.listener.BluetoothMatchingListener;
import com.redescooter.ecu.bsp.api.listener.BmsExchangeListener;
import com.redescooter.ecu.bsp.api.listener.EventListener;
import com.redescooter.ecu.bsp.api.listener.FaultReportListener;
import com.redescooter.ecu.bsp.api.listener.MeterListener;
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
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android_serialport_api.SerialPort;

public class SerialPortUtil {
    private final static String TAG = "SerialPortUtil";
    private String path0 = "/dev/ttyMT0";//串口0绝对地址
    private String path3 = "/dev/ttyMT3";//串口3绝对地址
    private int baudrate = 115200;//波特率

    //串口0
    private Thread receiveThread;
    private Thread sendThread;
    private SerialPort SerialPort0;
    private InputStream InputStream0;
    private OutputStream OutputStream0;
    //串口3
    private SerialPort SerialPort3;
    private InputStream InputStream3;
    private OutputStream OutputStream3;
    private Thread receiveThread3;
    private Thread sendThread3;

    private DataBean dataBean;
    private Context context = MainActivity.getContext();

    private volatile static SerialPortUtil serialPortUtil;

    private ListenerManager listenerManager = ListenerManager.getListenerManager();//接口管理   使用接口先new 一个


    private Bms bms = new Bms();
    private Ecu ecu = new Ecu();
    private Mcu mcu = new Mcu();
    private MeterMessage meterMessage = new MeterMessage();
    private ReportMessage reportMessage = new ReportMessage();
    private int code;

    public SerialPortUtil() {
    }

    public void initListener() {
        listenerManager.registerBluetoothMatching(new BluetoothMatchingListener() {
            @Override
            public int handle(List<BleScanMessage> uuid) {
                Log.e(TAG, "handle: " + uuid.toString());
                return 0;
            }
        });
        listenerManager.registerBmsExchange(new BmsExchangeListener() {
            @Override
            public void handle(BmsExchangeMessage msg) {
                Log.e(TAG, "handle: " + msg.hashCode());

            }
        });
        listenerManager.registerEvent(new EventListener() {
            @Override
            public void handle(String from, String event) {
                Log.e(TAG, "from=" + from + " event=" + event);

            }
        });
        listenerManager.registerFaultReport(new FaultReportListener() {
            @Override
            public void handle(ObdMessage msg) {
                Log.e(TAG, "handle: " + msg.hashCode());

            }
        });
        listenerManager.registerRfidOperation(new RfidOperationListener() {
            @Override
            public boolean handle(String rfid, String key) {
                Log.e(TAG, "Rfid=" + rfid + " key=" + key);
                return false;
            }
        });
        listenerManager.registerTimerReport(new TimerReportListener() {
            @Override
            public void handle(ReportMessage msg) {
                Log.e(TAG, "handle: " + msg.toString());

            }
        });
        listenerManager.registerMeter(new MeterListener() {
            @Override
            public void handle(MeterMessage msg) {
                Log.e(TAG, "MeterMessage: " + msg.toString());
            }
        });
    }
    private String data1 = "";
    public Handler serialHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String action = "";
            switch (msg.what){
                case 1:
                    String data = (String)msg.obj;
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(data);
                    data = m.replaceAll("");
                    Log.e(TAG, "串口0收到的数据为： " + data);

                    if (data.charAt(0) == '{' && data.charAt(data.length() - 1) == '}' && data.charAt(data.length() - 2) == '}') {
                        Log.e(TAG, "串口0数据接收成功");
                        data1 = "";
                        Message msg1 = new Message();
                        msg1.what = 2;
                        msg1.obj = data;
                        serialHandler.sendMessage(msg1);
                    } else if (data.charAt(0) == '{' && data.substring(2,7).equals("action")){
                        data1 = "";
                    }else {
                        Log.e(TAG, "串口0数据没接收完或者格式不对");
                        data1 = data1 + data;
                        if (data1.charAt(0) == '{' && data1.charAt(data1.length() - 1) == '}' && data1.charAt(data1.length() - 2) == '}') {

                            Message msg2 = new Message();
                            msg2.what = 1;
                            msg2.obj = data1;
                            serialHandler.handleMessage(msg2);
                        }
                    }
                    break;
                case 2:
                    String completeData = (String)msg.obj;
                    Gson gson = new Gson();
                    dataBean = new DataBean();
                    dataBean = gson.fromJson(completeData, DataBean.class);
                    action = dataBean.getAction();
                    if (action.equals("meter")) {
                        serialHandler.sendEmptyMessage(20);
                    } else if (action.equals("timerData")) {
                        serialHandler.sendEmptyMessage(21);
                    } else if (action.equals("notice")) {
                        serialHandler.sendEmptyMessage(22);
                    } else if (action.equals("bms")) {
                        serialHandler.sendEmptyMessage(23);
                    } else if (action.equals("RFID")) {
                        serialHandler.sendEmptyMessage(24);
                    } else if (action.equals("openLock") || action.equals("closeLock") || action.equals("openTrunkLock")
                            || action.equals("closeTrunkLock") || action.equals("getBMS")
                            || action.equals("getECU") || action.equals("getMCU") || action.equals("getReport")) {
                        serialHandler.sendEmptyMessage(25);
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
                    listenerManager.eventListener(dataBean.getdata().getFrom(),dataBean.getdata().getEvent());
                    break;
                case 23://bms
                    bms.setBatteryIds(dataBean.getdata().getBatteryIds());
                    break;
                case 24://RFID
                    boolean sendFlag = listenerManager.rfidOperationListener(dataBean.getdata().getRfid(),dataBean.getdata().getKey());
                    if (sendFlag){
                        sendSerialPort("{\"action\": \"RFID\",\"code\": \"1\", \"data\": {}}");
                    }else {
                        sendSerialPort("{\"action\": \"RFID\",\"code\": \"-1\", \"data\": {}}");
                    }
                    break;
                case 25:
                    action = dataBean.getAction();
                    code = dataBean.getCode();
                    if (action.equals("openLock")){
                        DeviceServiceTool.openLockFlag = false;
                    }else if (action.equals("closeLock")){
                        DeviceServiceTool.closeLockFlag = false;
                    }else if (action.equals("openTrunkLock")){
                        DeviceServiceTool.openTrunkLockFlag = false;
                    }else if (action.equals("closeTrunkLock")){
                        DeviceServiceTool.closeTrunkLockFlag = false;
                    }else if (action.equals("getBMS")){
                        DeviceServiceTool.getBMSFlag = false;
                    }else if (action.equals("getECU")){
                        ecu = new Ecu();
                        ecu.setBatteryCompartmentLockStatus(dataBean.getdata().getBatteryCompartmentLockStatus());
                        ecu.setBatteryTemperature(dataBean.getdata().getBatteryTemperature());
                        ecu.setCapacity(dataBean.getdata().getCapacity());
                        ecu.setClimbingAngle(dataBean.getdata().getClimbingAngle());
                        ecu.setExternalTemperature(dataBean.getdata().getExternalTemperature());
                        ecu.setGears(dataBean.getdata().getGears());
                        ecu.getGps().setGPRMC(dataBean.getdata().getGps().getGPRMC());
                        ecu.getGps().setGPGSV(dataBean.getdata().getGps().getGPGSV());
                        ecu.getGps().setGPGSA(dataBean.getdata().getGps().getGPGSA());
                        ecu.getGps().setGPGLL(dataBean.getdata().getGps().getGPGLL());
                        ecu.getGps().setGPGGA(dataBean.getdata().getGps().getGPGGA());
                        ecu.setInclinationAngle(dataBean.getdata().getInclinationAngle());
                        ecu.setLockStatus(dataBean.getdata().getLockStatus());
                        ecu.setMotorSpeed(dataBean.getdata().getMotorSpeed());
                        ecu.setSingleMileage(dataBean.getdata().getSingleMileage());
                        ecu.setSpeed(dataBean.getdata().getSpeed());
                        ecu.setLockStatus(dataBean.getdata().getLockStatus());
                        ecu.setTorsion(dataBean.getdata().getTorsion());
                        ecu.setTrunkLockStatus(dataBean.getdata().getTrunkLockStatus());
                        ecu.setTrunkTemperature(dataBean.getdata().getTrunkTemperature());
                        DeviceServiceTool.getECUFlag = false;
                    }else if (action.equals("getMCU")){
                        DeviceServiceTool.getMCUFlag = false;
                        mcu = new Mcu();
                        mcu.setControllerVersionNumber(dataBean.getdata().getControllerVersionNumber());
                        mcu.setFactoryVersion(dataBean.getdata().getFactoryVersion());
                        mcu.setMotorSpeed(dataBean.getdata().getMotorSpeed());
                    }else if (action.equals("getReport")) {
                        DeviceServiceTool.getReportFlag = false;
                        reportMessage = new ReportMessage();
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

                    }
                    break;

                case 30://串口3收到的数据到这
                    String data3 = (String)msg.obj;
                    Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher matcher = pattern.matcher(data3);
                    data3 = matcher.replaceAll("");
                    Log.e(TAG, "串口3收到的数据为： " + data3);
                    break;
            }
        }
    };


    /**
     * 打开串口
     */
    private void openSerial() {
        initListener();//注册接口
        //打开串口0
        try {
            SerialPort0 = new SerialPort(new File(path0), baudrate, 0);
            InputStream0 = SerialPort0.getInputStream();
            OutputStream0 = SerialPort0.getOutputStream();
            receiveThread();
            Toast.makeText(context,"串口0打开成功",Toast.LENGTH_SHORT).show();
            Log.e(TAG,"串口0打开成功");
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(context,"打开串口0异常" + e.toString(),Toast.LENGTH_SHORT).show();
            Log.e(TAG, "打开串口0异常：" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context,"打开串口0异常" + e.toString(),Toast.LENGTH_SHORT).show();
            Log.e(TAG, "打开串口0异常：" + e.toString());
        }

        //打开串口3
        try {
            SerialPort3 = new SerialPort(new File(path3), baudrate, 0);
            InputStream3 = SerialPort3.getInputStream();
            OutputStream3 = SerialPort3.getOutputStream();
            receiveThread3();
            Toast.makeText(context,"串口3打开成功",Toast.LENGTH_SHORT).show();
            Log.e(TAG,"串口3打开成功");
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(context,"打开串口3异常" + e.toString(),Toast.LENGTH_SHORT).show();
            Log.e(TAG, "打开串口3异常：" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context,"打开串口3异常" + e.toString(),Toast.LENGTH_SHORT).show();
            Log.e(TAG, "打开串口3异常：" + e.toString());
        }

    }

    /**
     * 接收串口0数据
     */
    private void receiveThread() {
        // 接收
        receiveThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int size;
                    try {
                        byte[] buffer = new byte[2048];
                        if (InputStream0 == null)
                            return;
                        size = InputStream0.read(buffer);
                        if (size > 0) {
                            byte[] bytes = new byte[size];
                            for (int i = 0;i < size;i++){
                                bytes[i] = buffer[i];
                            }
//                            Log.e("TAG","清零后的长度bytes=" + bytes.length + ",收到未除去零的长度buffer=" + buffer.length + "," + Arrays.toString(bytes));
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
     * 接收串口3数据
     */
    private void receiveThread3() {
        // 接收
        receiveThread3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int size;
                    try {
                        byte[] buffer = new byte[2048];
                        if (InputStream3 == null)
                            return;
                        size = InputStream3.read(buffer);
                        if (size > 0) {
                            byte[] bytes = new byte[size];
                            for (int i = 0;i < size;i++){
                                bytes[i] = buffer[i];
                            }
//                            Log.e("TAG","清零后的长度bytes=" + bytes.length + ",收到未除去零的长度buffer=" + buffer.length + "," + Arrays.toString(bytes));
                            String recinfo = new String(bytes,"GBK");

                            Message msg = new Message();
                            msg.what = 30;
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
        receiveThread3.start();
    }


    /**
     * 发送串口0指令（字符串）
     * @param data String数据指令
     */
    public void sendSerialPort(final String data){
        new Thread(){
            @Override
            public void run() {
                super.run();
                byte[] sendData = data.getBytes();
                Log.e(TAG, "sendSerialPort: 发送数据: " + Arrays.toString(sendData));
                try {
                    if (sendData.length > 0 && sendData != null) {
                        OutputStream0.write(sendData);
                        OutputStream0.flush();
                        Log.e(TAG, "sendSerialPort: 串口0数据发送成功: " + data);

                    }
                } catch (IOException e) {
                    Log.e(TAG, "sendSerialPort: 串口0数据发送失败："+e.toString());
                }

            }
        }.start();

        Toast.makeText(context,"sendSerialPort: 串口0数据发送成功: " + data,Toast.LENGTH_SHORT).show();

    }
    /**
     * 发送串口3指令（字符串）
     * @param data String数据指令
     */
    public void sendSerialPort3(final String data){
        new Thread(){
            @Override
            public void run() {
                super.run();
                byte[] sendData = data.getBytes();
                Log.e(TAG, "sendSerialPort: 发送数据: " + Arrays.toString(sendData));
                try {
                    if (sendData.length > 0 && sendData != null) {
                        OutputStream3.write(sendData);
                        OutputStream3.flush();
                        Log.e(TAG, "sendSerialPort: 串口3数据发送成功: " + data);

                    }
                } catch (IOException e) {
                    Log.e(TAG, "sendSerialPort: 串口3数据发送失败："+e.toString());
                }

            }
        }.start();

        Toast.makeText(context,"sendSerialPort: 串口3数据发送成功: " + data,Toast.LENGTH_SHORT).show();

    }

    /**
     * 关闭串口
     */
    public void closeSerialPort() {

        if (SerialPort0 != null) {
            SerialPort0.close();
        }
        if (InputStream0 != null) {
            try {
                InputStream0.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (OutputStream0 != null) {
            try {
                OutputStream0.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (SerialPort3 != null) {
            SerialPort3.close();
        }
        if (InputStream3 != null) {
            try {
                InputStream3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (OutputStream3 != null) {
            try {
                OutputStream3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static SerialPortUtil getSerialPortUtil(){
        if (serialPortUtil == null) {
            synchronized (SerialPortUtil.class) {
                Platform.initIO();
                Platform.SetGpioMode(59, 1);//URXD3
                Platform.SetGpioMode(60, 1);//UTXD3

                if (serialPortUtil == null) {
                    serialPortUtil = new SerialPortUtil();
                    serialPortUtil.openSerial();
                }
            }
        }
        return serialPortUtil;

    }



    public Bms getBms() {
        return bms;
    }

    public Ecu getEcu() {
        return ecu;
    }

    public Mcu getMcu() {
        return mcu;
    }

    public MeterMessage getMeterMessage() {
        return meterMessage;
    }

    public ReportMessage getReportMessage() {
        return reportMessage;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }
    public void setBms(Bms bms){
        this.bms = bms;
    }
    public void setEcu(Ecu ecu){
        this.ecu = ecu;
    }
    public void setMcu(Mcu mcu){
        this.mcu = mcu;
    }
    public void setReportMessage(ReportMessage reportMessage){
        this.reportMessage = reportMessage;
    }

}



