package com.example.androidcomponents.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReceiverPowerConnectivity : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(Intent.ACTION_POWER_CONNECTED)) {
            Log.d("Device power: ", "connected")
        } else if (intent.action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Log.d("Device power: ", "not connected")
        }
    }

}