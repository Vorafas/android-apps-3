package com.example.p0991_servicenotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String FILE_NAME = "filename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvInfo = findViewById(R.id.tvInfo);
        Intent intent = getIntent();

        String fileName = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(fileName)) {
            tvInfo.setText(fileName);
        }
    }

    public void onClickStart(View view) {
        startService(new Intent(this, MyService.class));
    }

    public void onClickStop(View view) {
        stopService(new Intent(this, MyService.class));
    }
}
