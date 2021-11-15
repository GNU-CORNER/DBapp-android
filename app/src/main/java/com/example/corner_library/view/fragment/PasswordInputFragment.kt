package com.example.corner_library.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.corner_library.R
import com.example.corner_library.databinding.FragmentPasswordInputBinding
import com.example.corner_library.view.activity.RegisterActivity
import com.example.corner_library.viewmodel.RegisterViewModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class PasswordInputFragment : Fragment() {
    private lateinit var binding: FragmentPasswordInputBinding
    private val viewModel: RegisterViewModel by activityViewModels()
    private lateinit var tlPassword: TextInputLayout
    private lateinit var tlPasswordConfirm: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordInputBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        tlPassword = binding.tilPassword
        tlPasswordConfirm = binding.tilPasswordConfirm

        setEditTextListener()

        return binding.root
    }

    private fun setEditTextListener() {
        tlPassword.editText!!.apply {
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
                    tlPassword.isErrorEnabled = false
                    tlPassword.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                } else {
                    tlPassword.endIconMode = TextInputLayout.END_ICON_NONE
                }
            }
        }

        tlPasswordConfirm.editText!!.apply {
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
                    tlPasswordConfirm.isErrorEnabled = false
                    tlPasswordConfirm.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                } else {
                    tlPasswordConfirm.endIconMode = TextInputLayout.END_ICON_NONE
                }
            }
        }
    }

    fun onButtonClick() {
        when {
            tlPassword.editText!!.text.isNullOrEmpty() -> {
                tlPassword.error = getString(R.string.password_input_message)
                showKeyboard(tlPassword.editText!!)
            }
            tlPasswordConfirm.editText!!.text.isNullOrEmpty() -> {
                tlPasswordConfirm.error = getString(R.string.password_input_message)
                showKeyboard(tlPasswordConfirm.editText!!)
            }
            !isPasswordEqual() -> {
                tlPasswordConfirm.error = getString(R.string.password_not_equal_message)
            }
            else -> {
                signUp()
            }
        }
    }

    // 키보드 보이기
    private fun showKeyboard(editText: EditText) {
        (activity as RegisterActivity).showKeyboard(editText)
    }

    // 비밀번호 일치 확인
    private fun isPasswordEqual() =
        tlPassword.editText!!.text.contentEquals(tlPasswordConfirm.editText!!.text)

    // 가입, 인증
    private fun signUp() {
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(viewModel.email.value!!, viewModel.password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FirebaseAuth.getInstance().currentUser
                        ?.sendEmailVerification()
                        ?.addOnCompleteListener { verifyTask ->
                            if (verifyTask.isSuccessful) {
                                viewModel.signUp()
                            } else {
                                Toast.makeText(context, "인증 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Log.i("LoginActivity", task.exception?.message!!)
                }
            }
    }
}