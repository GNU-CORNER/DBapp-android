package com.example.corner_library.utils

import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.corner_library.R

object CustomBindingAdapter {
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
}