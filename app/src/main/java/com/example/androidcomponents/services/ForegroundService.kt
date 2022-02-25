package com.example.androidcomponents.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.example.androidcomponents.APP_CONSTANTS
import com.example.androidcomponents.MainActivity
import com.example.androidcomponents.R

class ForegroundService : Service() {

    private lateinit var activityIntent: Intent

    override fun onCreate() {
        super.onCreate()
        activityIntent = Intent(this, MainActivity::class.java)
        createNotificationChannel()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(
            Runnable {
                while (true) {
                    Log.d("Service", "ForegroundService Starting")
                    Thread.sleep(3000)
                }
            }
        ).start()

        //val notificationManager = NotificationManagerCompat.from(this)
        //notificationManager.notify(0, notification)

        startForeground(
            100,
            createNotification(this, activityIntent).build()
        )

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun createPendingIntent(context: Context, intent: Intent): PendingIntent? {
        val activityPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        return activityPendingIntent
    }

    private fun createNotificationChannel() {
        val notificationChannel = NotificationChannel(
            APP_CONSTANTS.CHANNEL_ID,
            APP_CONSTANTS.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManagerService = getSystemService(NotificationManager::class.java)
        notificationManagerService.createNotificationChannel(notificationChannel)
    }

    private fun createNotification(context: Context, intent: Intent): NotificationCompat.Builder {
        val notification = NotificationCompat.Builder(context, APP_CONSTANTS.CHANNEL_ID)
            .setContentText("Foreground Service")
            .setContentText("Foreground service started...")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(createPendingIntent(context,  intent))

        return notification
    }

}