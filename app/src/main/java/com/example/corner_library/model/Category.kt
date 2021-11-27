package com.example.corner_library.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val title: String,
    val projectList: List<Project>
) : Parcelable
