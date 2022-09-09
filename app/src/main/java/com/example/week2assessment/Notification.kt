package com.example.week2assessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.week2assessment.databinding.FragmentNotificationBinding

class Notification : AppCompatActivity() {
//    private val model: SharedViewModel by activityViewModels()
    private val binding by lazy {
        FragmentNotificationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}