package com.example.p0872_servicebindserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private final String LOG_TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
    }

    public IBinder onBind(Intent bind) {
        Log.d(LOG_TAG, "MyService onBind");
        return new Binder();
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(LOG_TAG, "MyService onRebind");
    }

    public boolean onUnBind(Intent intent) {
        Log.d(LOG_TAG, "MyService onUnbind");
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }
}
