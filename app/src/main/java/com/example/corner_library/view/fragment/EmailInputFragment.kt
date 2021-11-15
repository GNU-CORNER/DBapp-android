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
import com.example.corner_library.databinding.FragmentEmailInputBinding
import com.example.corner_library.view.activity.RegisterActivity
import com.example.corner_library.viewmodel.RegisterViewModel
import com.google.android.material.textfield.TextInputLayout

class EmailInputFragment : Fragment() {
    private lateinit var binding: FragmentEmailInputBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmailInputBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setEditTextListener()

        return binding.root
    }

    private fun setEditTextListener() {
        binding.tilEmail.editText!!.apply {
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
                    binding.tilEmail.isErrorEnabled = false
                    binding.tilEmail.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                } else {
                    binding.tilEmail.endIconMode = TextInputLayout.END_ICON_NONE
                }
            }
        }
    }

    fun onButtonClick() {
        when {
            binding.tilEmail.editText!!.text.isNullOrEmpty() -> {
                binding.tilEmail.error = getString(R.string.email_input_message)
                showKeyboard(binding.tilEmail.editText!!)
            }
            !binding.tilEmail.editText!!.text.matches(regex) -> {
                binding.tilEmail.error = getString(R.string.email_regex_message)
                showKeyboard(binding.tilEmail.editText!!)
            }
            else -> {
                viewModel.nextPage()
                hideKeyboard(binding.tilEmail.editText!!)
            }
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

    companion object {
        private val regex = Regex("""^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@gnu.ac.kr$""")
    }
}