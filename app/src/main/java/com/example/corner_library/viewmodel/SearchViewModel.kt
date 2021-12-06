package com.example.corner_library.viewmodel

import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corner_library.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _suggestions = MutableLiveData<List<SpannableString>>(listOf())
    val suggestions: LiveData<List<SpannableString>> = _suggestions

    // 자동완성 검색
    fun searchSuggestion(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _suggestions.postValue(SearchRepository.searchSuggestion(query))
        }
    }
}