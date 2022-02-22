package com.example.androidcomponents

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ForegroundService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(
            Runnable {
                Log.d("Service", "ForegroundService Starting")
            }
        ).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}