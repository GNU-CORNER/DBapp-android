package com.example.corner_library.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corner_library.R
import com.example.corner_library.model.Project

class SearchViewModel : ViewModel() {
    private val _allSuggestions = MutableLiveData<List<Project>>()

    private val _filteredSuggestions = MutableLiveData<List<Project>>()
    val filteredSuggestions: LiveData<List<Project>> = _filteredSuggestions

    init {
        _allSuggestions.value = listOf(
//            Project("신선고", "냉장고 관리", R.drawable.logo2),
//            Project("빠스", "버스 승차 편의", R.drawable.logo2),
//            Project("메모리즘", "다양한 메모장", R.drawable.logo2),
//            Project("진화전쟁", "멀티 디펜스 게임", R.drawable.logo2),
//            Project("CATLAS", "학과 커뮤니티", R.drawable.logo2),
//            Project("로컬쉐어링", "진주 배달앱", R.drawable.logo2),
//            Project("Corner Archive", "프로젝트 정보 수집", R.drawable.logo2)
        )
    }

    fun filterSuggestions(query: String) {
        _filteredSuggestions.value = if (query.isEmpty()) {
            listOf()
        } else {
            _allSuggestions.value?.filter { it.projectName.contains(query) }
        }
    }
}