package com.example.corner_library.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val title: String,
    val projectList: List<Project>
) : Parcelable
