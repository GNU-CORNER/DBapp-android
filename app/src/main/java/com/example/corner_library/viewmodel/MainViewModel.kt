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
            Tag(11, "게임"),
        )

        _projects.value = listOf(
            Project(
                projectName = "CATLAS",
                teamName = "전우",
                logo = R.drawable.catlas_logo,
                members = "김우석, 전인혁",
                year = Date(2021, 9, 12),
                tags = listOf(_tags.value!![3], _tags.value!![6], _tags.value!![8]),
                subject = "컴퓨터과학과 커뮤니티",
                description = "사용되지 않는 CATLAS를 되살리기 위해 리뉴얼",
                scenario = listOf(R.drawable.catlas1, R.drawable.catlas2),
                gitAddress = "https://github.com/catlas",
                email = "wjs@gnu.co.kr"
            ),
            Project(
                projectName = "dxdata page",
                teamName = "dxdata",
                logo = R.drawable.dxdata_logo,
                members = "김학률, 권주현",
                year = Date(2021, 9, 12),
                tags = listOf(_tags.value!![6], _tags.value!![7]),
                subject = "dxdata의 홈페이지",
                description = "데이터를 이용한 플랫폼을 만드는 곳",
                scenario = listOf(R.drawable.dxdata1, R.drawable.dxdata2),
                gitAddress = "https://github.com/dxdata",
                email = "dxdata@dxdata.co.kr"
            ),
            Project(
                projectName = "ESD 핫딜",
                teamName = "ESD 핫딜",
                logo = R.drawable.esd_logo,
                members = "박주철, 황승현",
                year = Date(2020, 6, 12),
                tags = listOf(_tags.value!![1], _tags.value!![6], _tags.value!![8]),
                subject = "각종 소프트웨어 할인을 모아 놓은 곳",
                description = "각종 소프트웨어 할인을 모아 놓은 곳",
                scenario = listOf(R.drawable.esd1, R.drawable.esd2),
                gitAddress = "https://github.com/esd",
                email = "esd@gnu.co.kr"
            ),
            Project(
                projectName = "로컬 쉐어링",
                teamName = "전우",
                logo = R.drawable.logo3,
                members = "김우석, 전인혁",
                year = Date(2020, 6, 12),
                tags = listOf(_tags.value!![1], _tags.value!![8]),
                subject = "진주 음식 나눔 어플",
                description = "사용자의 지역 내의 개인과 자영업자들이 서로의 음식을 나누는 \"푸드쉐어링\"을 통해 잔반으로 인한 환경 문제를 예방하고 자영업자로 하여금 비용 절감의 효과도 누릴 수 있도록 돕는 앱서비스이다.",
                scenario = listOf(R.drawable.logo3, R.drawable.logo3),
                gitAddress = "https://github.com/local",
                email = "local@gnu.co.kr"
            ),
            Project(
                projectName = "Corner Archive",
                teamName = "구석방",
                logo = R.drawable.app_logo_purple,
                members = "김학률, 성재석",
                year = Date(2020, 6, 12),
                tags = listOf(_tags.value!![4], _tags.value!![5], _tags.value!![8]),
                subject = "컴퓨터과학과의 프로젝트 보관소",
                description = "컴퓨터과학과 특성상 매년 프로젝트가 모인다. 아이디어가 사라지는 것이 아까워서 보관할 수 있는 어플을 만들었다.\n후배들은 선배들의 작품을 보며 아이디어를 얻어갈 수 있다.",
                scenario = listOf(R.drawable.corner_archive1, R.drawable.corner_archive2),
                gitAddress = "https://github.com/local",
                email = "local@gnu.co.kr"
            ),
            Project(
                projectName = "인공지능 및 영상처리를 활용한 AI 챗봇 및 카카오톡 메롱 짱",
                teamName = "구석방 제작",
                logo = R.drawable.default_logo,
                members = "성재석,전인혁,황혁주,김학률",
                year = Date(2021, 6, 12),
                tags = listOf(_tags.value!![4], _tags.value!![9]),
                subject = "인공지능 AI 혁주모델을 활용하여 패턴인식 및 데이터 분석을 진행하여 답변하는 AI 챗봇",
                description = "사용자의 지역 내의 개인과 자영업자들이 서로의 음식을 나누는 \"푸드쉐어링\"을 통해 잔반으로 인한 환경 문제를 예방하고 자영업자로 하여금 비용 절감의 효과도 누릴 수 있도록 돕는 앱서비스. ",
                scenario = listOf(R.drawable.app_logo_purple, R.drawable.logo3),
                gitAddress = "https://github.com/chat",
                email = "chat@gnu.co.kr"
            ),
            Project(
                projectName = "AR Farming",
                teamName = "Code Farming",
                logo = R.drawable.default_logo,
                members = "윤재준, 박준수, 윤지연, 강도희",
                year = Date(2021, 6, 12),
                tags = listOf(_tags.value!![2], _tags.value!![4], _tags.value!![10]),
                subject = "증강 현실에서 이루어지는 농사 시뮬레이션",
                description = "대표적인 증강현실 게임 ‘포켓몬GO’는 거리를 돌아다니며 플레이해야 한다는 점과 증강현실에서 획득한 포켓몬과 상호작용하는 기능이 부족하다고 생각하여 그 점을 보완한 증강현실 게임이다.",
                scenario = listOf(R.drawable.ar_farm1, R.drawable.ar_farm2),
                gitAddress = "https://github.com/arfarming",
                email = "arfarming@gnu.co.kr"
            ),
            Project(
                projectName = "코디 쉐어",
                teamName = "codinginer",
                logo = R.drawable.default_logo,
                members = "김가은, 양소열, 이도연",
                year = Date(2021, 6, 12),
                tags = listOf(_tags.value!![3], _tags.value!![6], _tags.value!![8]),
                subject = "진주 내에서 필요한 옷을 빌릴 수 있는 앱",
                description = "옷을 대여해주는 사람은 사이즈 대여기간 가격을 제시해 게시글을 올리게 됩니다.\n대여하고 싶은 사람은 원하는 검색을 통해 나열된 목록 중 마음에 드는 옷을 대여하게 됩니다.",
                scenario = listOf(R.drawable.cody1, R.drawable.cody2),
                gitAddress = "https://github.com/cody_share",
                email = "cody_share@gnu.co.kr"
            ),
            Project(
                projectName = "진화전쟁",
                teamName = "Joy",
                logo = R.drawable.war_logo,
                members = "김민준",
                year = Date(2020, 6, 12),
                tags = listOf(_tags.value!![4], _tags.value!![10]),
                subject = "진화 컨셉을 가진 모바일 멀티플레이 게임",
                description = "멀티플레이 실시간 전략게임으로서 두명의 플레이어가 변화하는 시대의 흐름에 따라서 해당 시대를 대표하는 생명체들을 이용하여 전쟁을 펼치는 게임이다.",
                scenario = listOf(R.drawable.war1, R.drawable.war2),
                gitAddress = "https://github.com/war",
                email = "war@gnu.co.kr"
            ),
            Project(
                projectName = "메모리즘",
                teamName = "메모리즘",
                logo = R.drawable.memo_logo,
                members = "성재석, 서현지",
                year = Date(2021, 6, 12),
                tags = listOf(_tags.value!![4]),
                subject = "여러가지 형태의 메모 어플",
                description = "하나에 특화되어 있는 것이 아닌 여러 템플릿의 형태를 가진 메모장을 가지고 있다.\n이것으로 사용자는 여러 메모 어플을 설치할 필요가 없어진다.",
                scenario = listOf(R.drawable.memo1, R.drawable.memo2, R.drawable.memo3),
                gitAddress = "https://github.com/memo",
                email = "memo@gnu.co.kr"
            ),
            Project(
                projectName = "정갈",
                teamName = "정갈",
                logo = R.drawable.junggal_logo,
                members = "공종욱",
                year = Date(2021, 6, 12),
                tags = listOf(_tags.value!![1], _tags.value!![4]),
                subject = "반찬 나눔 어플",
                description = "내가 만든 반찬을 다른 사람과 소통하고 나눔을 통해 맛있는 한 끼를 해보자가 주목표이다\n더불어 내가 만든 반찬을 통해 자아실현을 통한 자기만족도를 높여 보자는 작은 소망을 담고 있다.",
                scenario = listOf(R.drawable.junggal1, R.drawable.junggal2, R.drawable.junggal3),
                gitAddress = "https://github.com/junggal",
                email = "junggal@gnu.co.kr"
            ),
        )

        _categories.value = listOf(
            Category("소프트웨어 설계 프로젝트 PBL", getProject("소프트웨어 설계 프로젝트 PBL")),
            Category("전공 종합 설계 프로젝트 PBL", getProject("전공 종합 설계 프로젝트 PBL")),
            Category("웹 프로젝트", getProject("웹 프로젝트")),
            Category("안드로이드", getProject("안드로이드")),
            Category("모아보기", _projects.value!!)

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