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
import com.example.corner_library.databinding.FragmentNameInputBinding
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
        binding.view = this

        setEditTextListener()

        return binding.root
    }

    private fun setEditTextListener() {
        binding.tilName.editText!!.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (text.isNullOrEmpty()) {
                        binding.tilName.error = getString(R.string.name_input_message)
                    } else {
                        viewModel.nextPage()
                    }

                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }

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

    fun nextPage() {
        if (binding.tilName.editText!!.text.isNullOrEmpty()) {
            binding.tilName.error = getString(R.string.name_input_message)
            binding.tilName.editText!!.requestFocus()

            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                binding.tilName.editText,
                InputMethodManager.SHOW_IMPLICIT
            )
        } else {
            viewModel.nextPage()
        }
    }
}