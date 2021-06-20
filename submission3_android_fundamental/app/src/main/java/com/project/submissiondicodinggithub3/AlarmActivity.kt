package com.project.submissiondicodinggithub3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.submissiondicodinggithub3.alarm.AlarmReceiver
import com.project.submissiondicodinggithub3.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding
    private lateinit var alarmReceiver: AlarmReceiver
    private var isAlarm = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alarmReceiver = AlarmReceiver()

        if (alarmReceiver.isAlarmSet(this)) {
            isAlarm = true
        }

        binding.switchReminder.isChecked = isAlarm
        binding.switchReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val alarmTime = "09:00"
                val alarmMessage = getString(R.string.alarm_message)
                alarmReceiver.setOnAlarm(this, alarmTime, alarmMessage)

                val text = getString(R.string.alarm_on_text)
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            } else {
                val text = getString(R.string.alarm_off_text)
                alarmReceiver.setOffAlarm(this)
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }
    }

}