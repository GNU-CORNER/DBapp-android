package com.example.corner_library.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Project(
    val projectName: String,
    val teamName: String,
    //    TODO: logo는 저장 방식에 따라 String 또는 Int로 바꿀 것, 임시로 Int
    val logo: Int,
    val members: String,
    val year: Date,
    val tags: List<Tag>,
    val subject: String,
    val description: String,
    //    TODO: scenario는 저장 방식에 따라 String 또는 Int로 바꿀 것, 임시로 Int
    val scenario: List<Int>,
    val gitAddress: String,
    val email: String,
) : Parcelable