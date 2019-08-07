package com.redescooter.ecu.bsp.api;

import android.util.Log;

import com.redescooter.ecu.bsp.api.enums.ObdItemEnum;
import com.redescooter.ecu.bsp.api.model.Bms;
import com.redescooter.ecu.bsp.api.model.Ecu;
import com.redescooter.ecu.bsp.api.model.Mcu;
import com.redescooter.ecu.bsp.api.model.ObdMessage;
import com.redescooter.ecu.bsp.api.model.ReportMessage;
import com.redescooter.ecu.bsp.api.model.RfidMessage;
import com.redescooter.ecu.bsp.api.serial.SerialPortUtil;
import com.redescooter.ecu.bsp.exception.DeviceServiceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

/**
 * 返回1为正常，0为数据未接收,负数为异常,异常未定
 *
 */
public class DeviceServiceTool implements DeviceService{

    private SerialPortUtil serialPortUtil = SerialPortUtil.getSerialPortUtil();
    public static boolean openLockFlag = true;
    public static boolean closeLockFlag = true;
    public static boolean openTrunkLockFlag = true;
    public static boolean closeTrunkLockFlag = true;
    public static boolean getBMSFlag = true;
    public static boolean getECUFlag = true;
    public static boolean getMCUFlag = true;
    public static boolean getReportFlag = true;
    public static boolean getBatteryIdsFlag = true;


    public static boolean openLockOverTimeFlag = false;
    public static boolean closeLockOverTimeFlag = false;
    public static boolean openTrunkLockOverTimeFlag = false;
    public static boolean closeTrunkLockOverTimeFlag = false;
    public static boolean getBMSOverTimeFlag = false;
    public static boolean getECUOverTimeFlag = false;
    public static boolean getMCUOverTimeFlag = false;
    public static boolean getReportOverTimeFlag = true;
    public static boolean getBatteryIdsOverTimeFlag = true;

    private static int overTime = 1000;

    public DeviceServiceTool(int overTime) {
        this.overTime = overTime;
    }

    public DeviceServiceTool() {
    }



    @Override
    public int openLock() throws DeviceServiceException{
        serialPortUtil.sendSerialPort("{\"action\":\"openLock\",\"code\":\"1\",\"data\":{}}");
        //设置超时时间
       Timer timer = new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               if (openLockFlag) {
                   openLockFlag = false;
                   openLockOverTimeFlag = true;
               }
           }
       },overTime);

        //保证能进 while
        openLockFlag = true;
        //等待串口数据回来或者超时时间到
        while (openLockFlag){
            delay(5);
        }

        //查看是否连接超时
        if (openLockOverTimeFlag){
            openLockOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }
        //得到code
        int code = serialPortUtil.getCode();
        serialPortUtil.setCode(0);
        Log.e("DeviceServiceTool","openLockFlagcode=" + code);
        return code;
    }

    @Override
    public int closeLock()  throws DeviceServiceException{
        serialPortUtil.sendSerialPort("{\"action\":\"closeLock\",\"code\":\"1\",\"data\":{}}");
        //设置超时时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (closeLockFlag) {
                    closeLockFlag = false;
                    closeLockOverTimeFlag = true;
                }
            }
        },overTime);

        //保证能进 while
        closeLockFlag = true;
        //等待串口数据回来或者超时时间到
        while (closeLockFlag){
            delay(5);
        }

        //查看是否连接超时
        if (closeLockOverTimeFlag){
            closeLockOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }
        //得到code
        int code = serialPortUtil.getCode();
        serialPortUtil.setCode(0);
        Log.e("DeviceServiceTool","closeLockFlagcode=" + code);
        return code;
    }

    @Override
    public int openTrunkLock()  throws DeviceServiceException{
        serialPortUtil.sendSerialPort("{\"action\":\"openTrunkLock\",\"code\":\"1\",\"data\":{}}");

        //设置超时时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (openTrunkLockFlag) {
                    openTrunkLockFlag = false;
                    openTrunkLockOverTimeFlag = true;
                }
            }
        },overTime);

        //保证能进 while
        openTrunkLockFlag = true;
        //等待串口数据回来或者超时时间到
        while (openTrunkLockFlag){
            delay(5);
        }

        //查看是否连接超时
        if (openTrunkLockOverTimeFlag){
            openTrunkLockOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }
        //得到code
        int code = serialPortUtil.getCode();
        serialPortUtil.setCode(0);
        Log.e("DeviceServiceTool","openTrunkLockFlagcode=" + code);
        return code;
    }

    @Override
    public int closeTrunkLock()  throws DeviceServiceException{
        serialPortUtil.sendSerialPort("{\"action\":\"closeTrunkLock\",\"code\":\"1\",\"data\":{}}");
        //设置超时时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (closeTrunkLockFlag) {
                    closeTrunkLockFlag = false;
                    closeTrunkLockOverTimeFlag = true;
                }
            }
        },overTime);

        //保证能进 while
        closeTrunkLockFlag = true;
        //等待串口数据回来或者超时时间到
        while (closeTrunkLockFlag){
            delay(5);
        }

        //查看是否连接超时
        if (closeTrunkLockOverTimeFlag){
            closeTrunkLockOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }
        //得到code
        int code = serialPortUtil.getCode();
        serialPortUtil.setCode(0);
        Log.e("DeviceServiceTool","closeTrunkLockcode=" + code);
        return code;
    }

    @Override
    public Bms getBMS() throws DeviceServiceException {
        serialPortUtil.sendSerialPort("{\"action\":\"getBMS\", \"code\":\"1\",\"data\":{}}");
        //设置超时时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getBMSFlag) {
                    getBMSFlag = false;
                    getBMSOverTimeFlag = true;
                }
            }
        },overTime);

        //保证能进 while
        getBMSFlag = true;
        //等待串口数据回来或者超时时间到
        while (getBMSFlag){
            delay(5);
        }

        //查看是否连接超时
        if (getBMSOverTimeFlag){
            getBMSOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }

        if (serialPortUtil.getBms() != null) {
            Bms bms = serialPortUtil.getBms();
            serialPortUtil.setBms(new Bms());
            return bms;
        }else {
            return new Bms();//如果读不到数据返回空对象
        }
    }

    @Override
    public Ecu getECU() throws DeviceServiceException {
        serialPortUtil.sendSerialPort("{\"action\":\"getECU\", \"code\":\"1\",\"data\":{}}");

        //设置超时时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getECUFlag) {
                    getECUFlag = false;
                    getECUOverTimeFlag = true;
                }
            }
        },overTime);

        //保证能进 while
        getECUFlag = true;
        //等待串口数据回来或者超时时间到
        while (getECUFlag){
            delay(5);
        }

        //查看是否连接超时
        if (getECUOverTimeFlag){
            getECUOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }

        if (serialPortUtil.getEcu() != null) {
            Ecu ecu = serialPortUtil.getEcu();
            serialPortUtil.setEcu(new Ecu());
            return ecu;
        }else {
            return new Ecu();//如果读不到数据返回null
        }
    }

    @Override
    public Mcu getMCU() throws DeviceServiceException {
        serialPortUtil.sendSerialPort("{\"action\":\"getMCU\", \"code\":\"1\",\"data\":{}}");

        //设置超时时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getMCUFlag) {
                    getMCUFlag = false;
                    getMCUOverTimeFlag = true;
                }
            }
        },overTime);

        //保证能进 while
        getMCUFlag = true;
        //等待串口数据回来或者超时时间到
        while (getMCUFlag){
            delay(5);
        }

        //查看是否连接超时
        if (getMCUOverTimeFlag){
            getMCUOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }

        if (serialPortUtil.getMcu() != null) {
            Mcu mcu = serialPortUtil.getMcu();
            serialPortUtil.setMcu(new Mcu());
            return mcu;
        }else {
            return new Mcu();//如果读不到数据返回null
        }
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
        return list;
    }

    @Override
    public ReportMessage getReport() throws DeviceServiceException {
        serialPortUtil.sendSerialPort("{\"action\":\"getReport\", \"code\":\"1\",\"data\":{}}");

        //设置超时时间
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (getReportFlag) {
                    getReportFlag = false;
                    getReportOverTimeFlag = true;
                }
            }
        },overTime);

        //保证能进 while
        getReportFlag = true;
        //等待串口数据回来或者超时时间到
        while (getReportFlag){
            delay(5);
        }

        //查看是否连接超时
        if (getReportOverTimeFlag){
            getReportOverTimeFlag = false;
            throw new DeviceServiceException("001","连接超时");
        }

        if (serialPortUtil.getReportMessage() != null) {
            ReportMessage reportMessage = serialPortUtil.getReportMessage();
            serialPortUtil.setReportMessage(new ReportMessage());
            Log.e("DeviceServiceTool", "getReport: " + serialPortUtil.toString());
            return reportMessage;
        }else {
            return new ReportMessage();//如果读不到数据返回null
        }

    }

    @Override
    public ObdMessage getOBD(ObdItemEnum obdItemEnum) throws DeviceServiceException {
        ObdMessage obdMessage = new ObdMessage();
        return obdMessage;
    }

    // 沉睡
    public void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
