package com.example.corner_library.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Suggestion(
    @SerializedName("suggestion")
    val name: String
) : Parcelable