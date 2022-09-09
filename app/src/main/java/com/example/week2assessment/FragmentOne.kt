package com.example.week2assessment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.week2assessment.DataHelper.DBHelper
import com.example.week2assessment.databinding.FragmentOneBinding

class FragmentOne : Fragment() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    private val binding by lazy {
        FragmentOneBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_FragmentOne_to_MainFragment)
        }
        binding.saveBtn.setOnClickListener {
            if(binding.Title.text.toString().isEmpty()||
                    binding.Category.text.toString().isEmpty()||
                    binding.Description.text.toString().isEmpty()||
                    binding.Date.text.toString().isEmpty()
            ){
                //do nothing
            }
            else{
                val db = activity?.let { it -> DBHelper(it, null) }
                db?.addData(binding.Title.text.toString(), binding.Category.text.toString(),
                    binding.Description.text.toString(), binding.Date.text.toString())
                db?.close()
                /**
                 * TODO: Need to set up a thing where this sends a broadcast to a receiver where
                 * it uses the information from date.text.toString to set up a broadcast receiver
                 **/
                findNavController().navigate(R.id.action_FragmentOne_to_MainFragment)
            }
        }
        return binding.root
    }

    companion object {
//        fun newInstance() = FragmentOne()
    }
}

