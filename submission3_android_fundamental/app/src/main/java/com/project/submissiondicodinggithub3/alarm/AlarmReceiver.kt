package com.project.submissiondicodinggithub3.alarm

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.project.submissiondicodinggithub3.MainActivity
import com.project.submissiondicodinggithub3.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver: BroadcastReceiver() {
    companion object {
        const val TYPE_REPEATING = "Github App"
        const val EXTRA_MESSAGE = "message"
        private const val ID_REPEATING = 101
        private const val TIME_FORMAT = "HH:mm"
        private const val ID_CHANNEL = "Alfi"
        private const val CHANNEL_NAME = "AlarmManager channel"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val title = TYPE_REPEATING
        val notificationId = ID_REPEATING
        if (message != null) {
            showAlarmNotification(context, title, message, notificationId)
        }
    }


    private fun showAlarmNotification(context: Context, title: String, message: String, notificationId: Int) {

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, ID_CHANNEL)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                ID_CHANNEL,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            builder.setChannelId(ID_CHANNEL)
            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManagerCompat.notify(notificationId, notification)
    }

    private fun isDateInvalid(date: String, format: String): Boolean {
        return try {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
            dateFormat.isLenient = false
            dateFormat.parse(date)
            false
        } catch (e: ParseException) {
            true
        }
    }

    fun setOnAlarm(context: Context, time: String, message: String) {
        if (isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)

        val arrayOfTime = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayOfTime[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(arrayOfTime[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    fun setOffAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REPEATING
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)
    }

    fun isAlarmSet(context: Context): Boolean {
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = ID_REPEATING

        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_NO_CREATE
        ) != null
    }

}