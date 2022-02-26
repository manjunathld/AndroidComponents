package com.example.androidcomponents.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReceiverBootComplete : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            //val intentForegroundService: Intent = Intent(context, ForegroundService::class.java)
            //context!!.startForegroundService(intentForegroundService)
        }
    }
}