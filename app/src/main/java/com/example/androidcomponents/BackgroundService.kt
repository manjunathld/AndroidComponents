package com.example.androidcomponents

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class BackgroundService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread (
            Runnable {
                while (true) {
                    //Toast.makeText(applicationContext, "New Thread Service", Toast.LENGTH_SHORT).show()
                    Log.d("Service", "New Thread Service, ${Thread.currentThread()}")
                    Thread.sleep(3000)
                }
            }
        ).start()

        Executors.newSingleThreadScheduledExecutor().schedule(Runnable {
            Log.d("Service", "Executors Thread Service, ${Thread.currentThread()}")
        }, 1, TimeUnit.SECONDS)

        Log.d("Service", "MainThread Service, ${Thread.currentThread()}")

        stopSelf()

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d("Service", "Service Destroyed, ${Thread.currentThread()}")
        super.onDestroy()
    }

}
