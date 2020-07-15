package com.example.p0971_servicebindclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String LOG_TAG = "myLogs";

    private boolean bound = false;
    ServiceConnection serviceConnection;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("com.example.p0972_servicebindserver.MyService");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View view) {
        startService(intent);
    }

    public void onClickStop(View view) {
        stopService(intent);
    }

    public void onClickBind(View view) {
        bindService(intent, serviceConnection, 0);
    }

    public void onClickUnBind(View view) {
        if (!bound) {
            return;
        }
        unbindService(serviceConnection);
        bound = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}
