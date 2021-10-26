package com.example.corner_library.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.corner_library.R
import com.example.corner_library.databinding.FragmentPasswordInputBinding
import com.example.corner_library.viewmodel.RegisterViewModel
import com.google.android.material.textfield.TextInputLayout

class PasswordInputFragment : Fragment() {
    private lateinit var binding: FragmentPasswordInputBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordInputBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.view = this

        setEditTextListener()

        return binding.root
    }

    private fun setEditTextListener() {
        binding.tilPassword.editText!!.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (text.isNullOrEmpty()) {
                        binding.tilPassword.error = getString(R.string.password_input_message)
                        return@setOnEditorActionListener true
                    } else {
                        return@setOnEditorActionListener false
                    }
                }

                return@setOnEditorActionListener false
            }

            doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    binding.tilPassword.isErrorEnabled = false
                    binding.tilPassword.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                } else {
                    binding.tilPassword.endIconMode = TextInputLayout.END_ICON_NONE
                }
            }
        }

        binding.tilPasswordConfirm.editText!!.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    when {
                        text.isNullOrEmpty() -> {
                            binding.tilPasswordConfirm.error =
                                getString(R.string.password_input_message)
                        }
                        !isPasswordEqual() -> {
                            binding.tilPasswordConfirm.error =
                                getString(R.string.password_not_equal_message)
                        }
                        else -> {
                            viewModel.nextPage()
                        }
                    }

                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }

            doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    binding.tilPasswordConfirm.isErrorEnabled = false
                    binding.tilPasswordConfirm.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                } else {
                    binding.tilPasswordConfirm.endIconMode = TextInputLayout.END_ICON_NONE
                }
            }
        }
    }

    fun nextPage() {
        when {
            binding.tilPassword.editText!!.text.isNullOrEmpty() -> {
                binding.tilPassword.error = getString(R.string.password_input_message)
                showKeyboard(binding.tilPassword.editText!!)
            }
            binding.tilPasswordConfirm.editText!!.text.isNullOrEmpty() -> {
                binding.tilPasswordConfirm.error = getString(R.string.password_input_message)
                showKeyboard(binding.tilPasswordConfirm.editText!!)
            }
            !isPasswordEqual() -> {
                binding.tilPasswordConfirm.error = getString(R.string.password_not_equal_message)
            }
            else -> {
                viewModel.nextPage()
            }
        }
    }

    private fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
            editText,
            InputMethodManager.SHOW_IMPLICIT
        )
    }

    private fun isPasswordEqual() =
        binding.tilPassword.editText!!.text.contentEquals(binding.tilPasswordConfirm.editText!!.text)
}