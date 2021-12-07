package com.example.corner_library.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.corner_library.data.model.Category

class ViewModelFactory(private val paramQuery: String?, private val paramCategory: Category?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchResultViewModel::class.java)) {
            SearchResultViewModel(paramQuery, paramCategory) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}