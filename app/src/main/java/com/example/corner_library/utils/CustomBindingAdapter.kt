package com.example.corner_library.utils

import android.text.SpannableString
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.adapters.CategoryAdapter
import com.example.corner_library.adapters.MiniProjectAdapter
import com.example.corner_library.adapters.ScenarioAdapter
import com.example.corner_library.adapters.SearchSuggestionAdapter
import com.example.corner_library.data.model.Category
import com.example.corner_library.data.model.Project

object CustomBindingAdapter {
    @BindingConversion
    @JvmStatic
    fun convertBooleanToVisibility(visible: Boolean): Int {
        return if (visible) View.VISIBLE else View.GONE
    }

    // 회원가입 페이지 번호에 따른 버튼 스타일 변경
    @BindingAdapter("buttonType")
    @JvmStatic
    fun setButtonStyle(button: Button, maxPage: Boolean) {
        val context = button.context
        val style = if (maxPage) {
            R.string.btn_register_complete to R.color.personal_color
        } else {
            R.string.btn_register_next to R.color.sub_color
        }

        button.text = context.getString(style.first)
        button.setBackgroundColor(ContextCompat.getColor(context, style.second))
    }

    @BindingAdapter("categories")
    @JvmStatic
    fun setCategories(recyclerView: RecyclerView, categories: List<Category>?) {
        val adapter = recyclerView.adapter as CategoryAdapter
        adapter.submitList(categories)
    }

    @BindingAdapter("projects")
    @JvmStatic
    fun setProjects(recyclerView: RecyclerView, projects: List<Project>?) {
        val adapter = recyclerView.adapter as MiniProjectAdapter
        adapter.submitList(projects)
    }

    @BindingAdapter("scenarios")
    @JvmStatic
    fun setScenarios(recyclerView: RecyclerView, scenarios: List<Int>?) {
        val adapter = recyclerView.adapter as ScenarioAdapter
        adapter.submitList(scenarios)
    }

    @BindingAdapter("suggestions")
    @JvmStatic
    fun setSuggestions(recyclerView: RecyclerView, suggestions: List<SpannableString>) {
        val adapter = recyclerView.adapter as SearchSuggestionAdapter
        adapter.submitList(suggestions)

        // 데이터가 추가됐을 경우 최상단으로 스크롤
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    positionStart,
                    0
                )
            }
        })
    }
}