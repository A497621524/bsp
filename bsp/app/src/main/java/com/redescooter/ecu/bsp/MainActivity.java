package com.redescooter.ecu.bsp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.redescooter.ecu.bsp.api.ListenerManager;
import com.redescooter.ecu.bsp.api.R;
import com.redescooter.ecu.bsp.api.listener.BluetoothMatchingListener;
import com.redescooter.ecu.bsp.api.listener.MeterListener;
import com.redescooter.ecu.bsp.api.model.BleScanMessage;
import com.redescooter.ecu.bsp.api.model.MeterMessage;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MeterListener{

    private ListenerManager listenerManager = new ListenerManager();
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listenerManager.register(this);

        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listenerManager.asd();

            }
        });

    }

    @Override
    public void handle(MeterMessage msg) {
        Log.e("TAG", "handle: " + msg.hashCode());
    }


}
