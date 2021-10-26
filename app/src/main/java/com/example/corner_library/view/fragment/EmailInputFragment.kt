package com.example.corner_library.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.corner_library.R
import com.example.corner_library.databinding.FragmentEmailInputBinding
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
        binding.view = this

        setEditTextListener()

        return binding.root
    }

    private fun setEditTextListener() {
        binding.tilEmail.editText!!.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    nextPage()

                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }

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

    fun nextPage() {
        when {
            binding.tilEmail.editText!!.text.isNullOrEmpty() -> {
                binding.tilEmail.error = getString(R.string.email_input_message)
                showKeyboard()
            }
            !binding.tilEmail.editText!!.text.matches(regex) -> {
                binding.tilEmail.error = getString(R.string.email_regex_message)
                showKeyboard()
            }
            else -> {
                viewModel.nextPage()
            }
        }
    }

    private fun showKeyboard() {
        binding.tilEmail.editText!!.requestFocus()
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
            binding.tilEmail.editText,
            InputMethodManager.SHOW_IMPLICIT
        )
    }

    companion object {
        private val regex = Regex("""^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@gnu.ac.kr$""")
    }
}