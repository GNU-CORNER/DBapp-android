package com.example.corner_library.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corner_library.R
import com.example.corner_library.data.model.Category
import com.example.corner_library.data.model.Project
import com.example.corner_library.data.model.Tag
import java.util.*

class MainViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    private val _tags = MutableLiveData<List<Tag>>()
    private val _projects = MutableLiveData<List<Project>>()

    val categories: LiveData<List<Category>> = _categories
    val tags: LiveData<List<Tag>> = _tags
    val projects: LiveData<List<Project>> = _projects

    init {
        _tags.value = listOf(
            Tag(1, "기초 설계 프로젝트 PBL"),
            Tag(2, "소프트웨어 설계 프로젝트 PBL"),
            Tag(3, "전공 종합 설계 프로젝트 PBL"),
            Tag(4, "웹 프로젝트"),
            Tag(5, "안드로이드"),
            Tag(6, "IOS"),
            Tag(7, "자바스크립트"),
            Tag(8, "static"),
            Tag(9, "React"),
            Tag(10, "인공지능"),
            )

        _projects.value = listOf(
            Project(
                projectName = "CATLAS",
                teamName = "전우",
                logo = R.drawable.logo2,
                members = "김우석, 전인혁",
                year = Date(2021, 9, 12),
                tags = listOf(_tags.value!![3], _tags.value!![6], _tags.value!![8]),
                subject = "컴퓨터과학과 커뮤니티",
                description = "사용되지 않는 CATLAS를 되살리기 위해 리뉴얼",
                scenario = listOf(R.drawable.app_logo, R.drawable.app_logo_purple),
                gitAddress = "https://github.com/catlas",
                email = "wjs@gnu.co.kr"
            ),
            Project(
                projectName = "dxdata page",
                teamName = "dxdata",
                logo = R.drawable.logo2,
                members = "김학률, 권주현",
                year = Date(2021, 9, 12),
                tags = listOf(_tags.value!![6], _tags.value!![7]),
                subject = "dxdata의 홈페이지",
                description = "데이터를 이용한 플랫폼을 만드는 곳",
                scenario = listOf(R.drawable.logo, R.drawable.logo2),
                gitAddress = "https://github.com/dxdata",
                email = "dxdata@dxdata.co.kr"
            ),
            Project(
                projectName = "ESD 핫딜",
                teamName = "ESD 핫딜",
                logo = R.drawable.logo3,
                members = "박주철, 황승현",
                year = Date(2020, 6, 12),
                tags = listOf(_tags.value!![1], _tags.value!![6], _tags.value!![8]),
                subject = "각종 소프트웨어 할인을 모아 놓은 곳",
                description = "각종 소프트웨어 할인을 모아 놓은 곳",
                scenario = listOf(R.drawable.app_logo, R.drawable.logo2),
                gitAddress = "https://github.com/esd",
                email = "esd@gnu.co.kr"
            ),
            Project(
                projectName = "로컬쉐어링",
                teamName = "전우",
                logo = R.drawable.logo3,
                members = "김우석, 전인혁",
                year = Date(2020, 6, 12),
                tags = listOf(_tags.value!![1], _tags.value!![8]),
                subject = "진주 배달앱",
                description = "진주의 음식점을을 모아 놓은 배달앱",
                scenario = listOf(R.drawable.logo3, R.drawable.logo3),
                gitAddress = "https://github.com/local",
                email = "local@gnu.co.kr"
            ),
            Project(
                projectName = "Corner Archive",
                teamName = "구석방",
                logo = R.drawable.app_logo_purple,
                members = "김우석, 전인혁",
                year = Date(2020, 6, 12),
                tags = listOf(_tags.value!![2], _tags.value!![5], _tags.value!![8]),
                subject = "진주 배달앱",
                description = "진주의 음식점을을 모아 놓은 배달앱",
                scenario = listOf(R.drawable.logo3, R.drawable.logo3),
                gitAddress = "https://github.com/local",
                email = "local@gnu.co.kr"
            ),
            Project(
                projectName = "인공지능 및 영상처리를 활용한 AI 챗봇 및 카카오톡 메롱 짱",
                teamName = "구석방 제작",
                logo = R.drawable.logo3,
                members = "성재석,전인혁,황혁주,김학률",
                year = Date(2021, 6, 12),
                tags = listOf(_tags.value!![4], _tags.value!![9]),
                subject = "인공지능 AI 혁주모델을 활용하여 패턴인식 및 데이터 분석을 진행하여 답변하는 AI 챗봇",
                description = "사용자의 지역 내의 개인과 자영업자들이 서로의 음식을 나누는 \"푸드쉐어링\"을 통해 잔반으로 인한 환경 문제를 예방하고 자영업자로 하여금 비용 절감의 효과도 누릴 수 있도록 돕는 앱서비스. ",
                scenario = listOf(R.drawable.app_logo_purple, R.drawable.logo3),
                gitAddress = "https://github.com/chat",
                email = "chat@gnu.co.kr"
            ),
        )

//        _categories.value = listOf(
//            Category("기초 설계 프로젝트 PBL", _projects.value!!),
//            Category("소프트웨어 설계 PBL", _projects.value!!),
//            Category("전공 종합 설계 PBL", _projects.value!!),
//            Category("안드로이드", _projects.value!!)
//        )

        _categories.value = listOf(
            Category("기초 설계 프로젝트 PBL", getProject("기초 설계 프로젝트 PBL")),
            Category("소프트웨어 설계 프로젝트 PBL", getProject("소프트웨어 설계 프로젝트 PBL")),
            Category("전공 종합 설계 프로젝트 PBL", getProject("전공 종합 설계 프로젝트 PBL")),
            Category("안드로이드", getProject("안드로이드"))
        )

    }
    private fun getProject(tagName: String) : List<Project> {
        val filterList : List<Project>

        filterList = _projects.value!!.filter{
            it.tags.filter { it.name == tagName }.isNotEmpty()
        }
        Log.d("디버그", tagName + filterList.toString())

        return filterList
    }
}