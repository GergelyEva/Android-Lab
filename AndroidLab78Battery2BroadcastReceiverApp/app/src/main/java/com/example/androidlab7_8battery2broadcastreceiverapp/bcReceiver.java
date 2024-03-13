package com.example.androidlab7_8battery2broadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class bcReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case Intent.ACTION_BATTERY_LOW:
                    Log.i("BroadcastReceiver", "Battery is low");
                    Toast.makeText(context, "Battery is low", Toast.LENGTH_LONG).show();
                    break;
                case Intent.ACTION_BATTERY_CHANGED:
                    Log.i("BroadcastReceiver", "Battery status changed");
                    Toast.makeText(context, "Battery status changed", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}