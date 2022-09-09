package com.example.week2assessment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventData(
    val key: String,
    val title: String,
    val category: String,
    val description: String,
    val date: String
) : Parcelable

