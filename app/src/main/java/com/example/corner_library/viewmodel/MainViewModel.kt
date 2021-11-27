package com.example.corner_library.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corner_library.R
import com.example.corner_library.model.Category
import com.example.corner_library.model.Project

class MainViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    private val _projects = MutableLiveData<List<Project>>()

    val categories: LiveData<List<Category>> = _categories
    val projects: LiveData<List<Project>> = _projects

    init {
        _projects.value = listOf(
            Project(
                name = "신선고라너리낭리ㅏㅜㅏㅣㅁ러;ㅣㄴ알;ㅏㅁㅇ너라ㅣ어ㅏㅣㄹㅇㄴ",
                subject = "냉장고 관리",
                logo = R.drawable.logo2
            ),
            Project(
                name = "전인혁",
                subject = "인혁이 관리 ㄹㄴ아ㅓㅣ롱노리ㅏㅓ모니ㅓ로언머ㅏ로어ㅏㄴ모리ㅏㅁㄴ오리ㅏㅓ옴나ㅓ롱니ㅏㅓㅗㄹ미ㅗㅓ롱머ㅏ니ㅗㄹ아ㅣㅓㅁ뇌라",
                logo = R.drawable.logo3
            ),
            Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2),
            Project(name = "로컬쉐어링", subject = "진주 배달앱", logo = R.drawable.logo3),
            Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2)
        )

        _categories.value = listOf(
            Category("기초설계프로젝트 PBL", _projects.value!!),
            Category("소프트웨어설계 PBL", _projects.value!!),
            Category("전공종합설계 PBL", _projects.value!!),
            Category("안드로이드", _projects.value!!)
        )
    }
}