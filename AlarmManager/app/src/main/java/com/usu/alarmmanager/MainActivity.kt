package com.usu.alarmmanager

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.usu.alarmmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

//        create channel
        val channel = NotificationChannel("mychannel", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
        channel.description = "My channel for reminders"
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        binding.button.setOnClickListener {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
            val intent = Intent(this, ReminderService::class.java)
            intent.putExtra("kind", 10)
            val pendingIntent = PendingIntent.getService(this, 0, intent, 0)
            alarmManager?.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10000,
                pendingIntent
            )
        }

        setContentView(binding.root)
    }
}