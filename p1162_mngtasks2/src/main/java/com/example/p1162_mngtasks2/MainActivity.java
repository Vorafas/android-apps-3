package com.example.p1162_mngtasks2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "myLogs";
    List<ActivityManager.RunningTaskInfo> list;
    ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getResources().getString(R.string.app_name) + " : " + getLocalClassName());
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
    }

    public void onClick(View view) {
        startActivity(new Intent("mngtasks1_activity_c")
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET));
    }

    public void onInfoClick(View view) {
        list = activityManager.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo task : list) {
            if (task.baseActivity.flattenToShortString().startsWith("com.example.p116")) {
                Log.d(LOG_TAG, "---------------");
                Log.d(LOG_TAG, "Count: " + task.numActivities);
                Log.d(LOG_TAG, "Root: " + task.baseActivity.flattenToShortString());
                Log.d(LOG_TAG, "Top: " + task.topActivity.flattenToShortString());
            }
        }
    }
}
