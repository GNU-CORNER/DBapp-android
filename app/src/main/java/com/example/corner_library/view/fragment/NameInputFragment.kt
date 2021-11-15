package com.example.corner_library.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.corner_library.R
import com.example.corner_library.databinding.FragmentNameInputBinding
import com.example.corner_library.view.activity.RegisterActivity
import com.example.corner_library.viewmodel.RegisterViewModel
import com.google.android.material.textfield.TextInputLayout

class NameInputFragment : Fragment() {
    private lateinit var binding: FragmentNameInputBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNameInputBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setEditTextListener()

        return binding.root
    }

    private fun setEditTextListener() {
        binding.tilName.editText!!.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) { // 키보드의 다음 버튼 클릭 시
                    onButtonClick()

                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }
            // 텍스트 입력 변경 시
            doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    binding.tilName.isErrorEnabled = false
                    binding.tilName.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                } else {
                    binding.tilName.endIconMode = TextInputLayout.END_ICON_NONE
                }
            }
        }
    }

    fun onButtonClick() {
        if (binding.tilName.editText!!.text.isNullOrEmpty()) {
            binding.tilName.error = getString(R.string.name_input_message)
            showKeyboard(binding.tilName.editText!!)
        } else {
            viewModel.nextPage()
            hideKeyboard(binding.tilName.editText!!)
        }
    }

    // 키보드 보이기
    private fun showKeyboard(editText: EditText) {
        (activity as RegisterActivity).showKeyboard(editText)
    }

    // 키보드 숨김
    private fun hideKeyboard(editText: EditText) {
        (activity as RegisterActivity).hideKeyboard(editText)
    }
}