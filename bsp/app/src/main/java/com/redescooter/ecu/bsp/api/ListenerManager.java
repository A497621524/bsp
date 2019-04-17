package com.redescooter.ecu.bsp.api;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.redescooter.ecu.bsp.api.listener.AbsListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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


}
