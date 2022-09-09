package com.example.week2assessment

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.week2assessment.databinding.FragmentDetailsBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailsFragment : Fragment() {
    private val model: SharedViewModel by activityViewModels()
    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.DetailsTitle.text = model.importantTitle
        binding.DetailsCategory.text = model.importantCategory
        binding.DetailsDescription.text = model.importantDescription
        binding.DetailsDate.text = model.importantDate
        binding.detailsBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_DetailsFragment_to_MainFragment)
        }
        binding.detailsEditBtn.setOnClickListener {
            findNavController().navigate(R.id.action_DetailsFragment_to_ModifyEventFragment)
        }

        createNotificationChannel()
        binding.AlarmButton.setOnClickListener {
            scheduleNotification()
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification() {
        val intent = Intent(activity, Notification::class.java)
        val title = binding.DetailsTitle.text.toString()
        val message = binding.DetailsCategory.text.toString()+ " "+ binding.DetailsDescription.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)
        val pendingIntent = PendingIntent.getBroadcast(
            activity,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, message)
    }
    private fun showAlert(time: Long, title: String, message: String)
    {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(activity?.applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(activity?.applicationContext)

        AlertDialog.Builder(activity)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage: " + message +
                        "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }

    private fun createNotificationChannel() {
        val name = "Notification Channel"
        val desc = "A description of the channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        val notificationManager = requireActivity().getSystemService(NOTIFICATION_SERVICE)  as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


    @SuppressLint("SimpleDateFormat")
    private fun getTime(): Long {
        val date: Date
        val format = SimpleDateFormat("MM-dd-yyyy")
        val text = binding.DetailsDate.text.toString()
        date = format.parse(text) as Date
        val dateAsLong = date.time
        dateAsLong.toLong()
        return dateAsLong
    }

    companion object {

    }
}