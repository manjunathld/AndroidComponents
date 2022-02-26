package com.example.androidcomponents.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class BoundService : Service() {

    private val mBinder: Binder = Binder()

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onBind(p0: Intent?): IBinder {
        Thread(
            Runnable {
                while (true) {
                    Log.d("Service is", "Bounded")
                    Thread.sleep(1000)
                }
            }
        ).start()

        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Thread(
            Runnable {
                Log.d("Service is", "UnBounded")
            }
        ).start()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}