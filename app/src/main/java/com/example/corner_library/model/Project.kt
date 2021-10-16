package com.example.corner_library.model

data class Project(
    val name: String,
    val subject: String,
//    TODO: logo는 저장 방식에 따라 String 또는 Int로 바꿀 것, 임시로 Int
    val logo: Int,
    )

