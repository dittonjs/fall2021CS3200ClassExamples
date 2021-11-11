package com.usu.alarmmanager

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ReminderService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // display notification
        println("I GOT RAN!!!!!!!")
//            intent?.getIntArrayExtra("kind")


        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, "mychannel")
            .setContentTitle("I am reminding you!")
            .setContentText("Do you feel reminded yet?")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val manager = NotificationManagerCompat.from(this)
        manager.notify(1, builder.build())

        return super.onStartCommand(intent, flags, startId)
    }
}