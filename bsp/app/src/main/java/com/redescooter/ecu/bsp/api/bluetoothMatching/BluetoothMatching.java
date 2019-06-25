package com.redescooter.ecu.bsp.api.bluetoothMatching;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import com.redescooter.ecu.bsp.api.ListenerManager;
import com.redescooter.ecu.bsp.api.model.BleScanMessage;
import com.redescooter.ecu.bsp.api.serial.SerialPortUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无线信号多为mW级别，所以对它进行了极化，转化为dBm而已，不表示信号是负的。
 * 　　1mW就是0dBm，小于1mW就是负数的dBm数。
 * 　　dBm 定义的是 miliwatt（米瓦特）
 *
 *  发射端一般是正值，数值越大发射功率越大；接收端一般是负值，数值越小代表灵敏度越高。
 *
 *      RSSI值的定义由WIFI芯片厂家自己定，如一个厂家可以定义RSSI = 80对应-65dBm；
 *      另一个可以定义RSSI = 80对应-60dBm等。 Qualcomm Atheros的RSSI一般是0-127。
 *      对于相同信号强度，实际的RSSI值可能不同，
 *
 */
public class BluetoothMatching {

    private BluetoothAdapter mBluetoothAdapter;
    private static final String TAG = "BluetoothMatching";
    private boolean blueFlag = false;
    private Context context;
    private List<BleScanMessage> bleScanMessages = new ArrayList<>();

    private ListenerManager listenerManager = ListenerManager.getListenerManager();

    public BluetoothMatching(Context context) {
        this.context = context;
    }

    public void start(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                blueFlag = true;
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
                context.registerReceiver(mReceiver,filter);
                IntentFilter filter2=new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
                context.registerReceiver(mReceiver,filter2);

                if (mBluetoothAdapter != null) {
                    if (!mBluetoothAdapter.isEnabled()) {
                        //若没打开则打开蓝牙
                        mBluetoothAdapter.enable();
                    }
                }else {
                    Log.e(TAG, "该设备不支持蓝牙");
                }
                mBluetoothAdapter.startDiscovery();
            }
        }.start();
    }

    public void close(){
        blueFlag = false;
        //解除注册
        context.unregisterReceiver(mReceiver);
        Log.e("destory","解除注册");
    }

    //定义广播接收
    private BroadcastReceiver mReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            Log.e(TAG, "搜索开始");
            Log.e("ywq", action);
            if(action.equals(BluetoothDevice.ACTION_FOUND))
            {
                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if(device.getBondState()==BluetoothDevice.BOND_BONDED) {    //显示已配对设备
                    short rssi = intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI);
                    Log.e(TAG,"\n"+device.getName()+"==>"+device.getAddress()+"\n" + rssi);
                    bleScanMessages.add(new BleScanMessage(device.getAddress(),rssi));
                }else if(device.getBondState()!=BluetoothDevice.BOND_BONDED) {
                    short rssi = intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI);
                    Log.e(TAG,"\n"+device.getName()+"==>"+device.getAddress()+"\n");
                    bleScanMessages.add(new BleScanMessage(device.getAddress(),rssi));
                }

            }else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                Log.e(TAG, "搜索完成" + bleScanMessages);
                if (bleScanMessages.size() != 0 || bleScanMessages != null) {
                    listenerManager.bluetoothMatchingListener(bleScanMessages);
                }
                if (blueFlag){
                    boolean b =  mBluetoothAdapter.startDiscovery();
                    if (!b){
                        mBluetoothAdapter.startDiscovery();
                    }
                }
            }

        }


    };



}
