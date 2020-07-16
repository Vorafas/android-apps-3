package com.example.p1161_mngtask1;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class ActivityA extends MainActivity {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, ActivityB.class));
    }
}
