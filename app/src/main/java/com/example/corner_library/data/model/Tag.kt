package com.example.corner_library.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(
    val id: Number,
    val name: String,
) : Parcelable {

}