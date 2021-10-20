package com.example.corner_library.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

object CustomBindingAdapter {
    @BindingAdapter("currentItem")
    @JvmStatic
    fun changePage(viewPager2: ViewPager2, itemPosition: Int) {
        viewPager2.currentItem = itemPosition - 1
        (viewPager2.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            viewPager2.windowToken,
            0
        )
    }
}