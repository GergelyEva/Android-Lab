package com.example.androidlab7_8battery2broadcastreceiverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        IntentFilter intentFilter1 = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        bcReceiver objReceiver1 = new bcReceiver();
        registerReceiver(objReceiver1, intentFilter1);

        IntentFilter intentFilter2 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        bcReceiver objReceiver2 = new bcReceiver();
        registerReceiver(objReceiver2, intentFilter2);

    }
}