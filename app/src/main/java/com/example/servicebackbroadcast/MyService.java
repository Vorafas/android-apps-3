package com.example.servicebackbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    private final String LOG_TAG = "myLogs";
    private ExecutorService executorService;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");

        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        int task = intent.getIntExtra(MainActivity.PARAM_TASK, 0);

        MyRun myRun = new MyRun(startId, time, task);
        executorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyRun implements Runnable {
        private int time, startId, task;

        public MyRun(int startId, int time, int task) {
            this.startId = startId;
            this.task = task;
            this.time = time;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                // Сообщаем о старте задачи
                intent.putExtra(MainActivity.PARAM_TASK, task);
                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_START);
                sendBroadcast(intent);

                // Начинем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                // Сообщаем об окончании задачи
                intent.putExtra(MainActivity.PARAM_STATUS, MainActivity.STATUS_FINISH);
                intent.putExtra(MainActivity.PARAM_RESULT, time * 100);
                sendBroadcast(intent);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
            stop();
        }

        private void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}
