package com.redescooter.ecu.bsp.api;

import android.util.Log;
import android.widget.Toast;

import com.redescooter.ecu.bsp.api.listener.AbsListener;

/**
 * 所有Listener只能注册一次
 * 如果重复注册后面的覆盖前面的
 */
public class ListenerManager {
    private AbsListener listener;

    public void registe(AbsListener listener){
        this.listener = listener;
    }

    public void unregiste(AbsListener listener){
        this.listener = null;
    }

}
