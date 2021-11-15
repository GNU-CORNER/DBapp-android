package com.example.corner_library.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    private val _currentPage = MutableLiveData<Int>()

    val currentPage: LiveData<Int> = _currentPage
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordConfirm = MutableLiveData<String>()

    init {
        _currentPage.value = 1
    }

    fun nextPage() {
        if (_currentPage.value!! < NUM_PAGES) _currentPage.value = _currentPage.value?.inc()
    }

    fun prevPage() {
        if (_currentPage.value!! > 1) _currentPage.value = _currentPage.value?.dec()
    }

    fun signUp() {
        //TODO 서버의 회원가입 API 통신 추가
        nextPage()
    }

    companion object {
        const val NUM_PAGES = 4
    }
}