package com.example.p0991_servicenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
        sendNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification() {
        // 1-я часть
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FILE_NAME, "someFile");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // 2-я часть
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notification's title")
                .setContentText("Notification's text")
                .setTicker("Text in status bar")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Отправка
        notificationManager.notify(1, notification.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
