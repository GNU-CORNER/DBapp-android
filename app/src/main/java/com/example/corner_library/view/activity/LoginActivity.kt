package com.example.corner_library.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.corner_library.R
import com.example.corner_library.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()

        val logoAni = AnimationUtils.loadAnimation(this, R.anim.slide_logo)
        val inputAni = AnimationUtils.loadAnimation(this, R.anim.input_info)

        binding.splashLogo.startAnimation(logoAni)
        binding.inputInfo.startAnimation(inputAni)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.splashLogo.setImageResource(R.drawable.login_app_logo)
        }, 1300)

    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.view = this
    }

    // 로그인
    fun startLogin() {
        if (binding.etId.text.isEmpty() || binding.etPassword.text.isEmpty()) {
            showToast(getString(R.string.empty_input_message))
        } else {
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(
                    binding.etId.text.toString(),
                    binding.etPassword.text.toString()
                )
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (FirebaseAuth.getInstance().currentUser?.isEmailVerified!!) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            showToast(getString(R.string.not_verified_link_message))
                        }
                    } else {
                        when (task.exception!!.localizedMessage) {
                            getString(R.string.badly_format_exception) -> {
                                showToast(getString(R.string.badly_format_message))
                            }
                            getString(R.string.no_user_exception) -> {
                                showToast(getString(R.string.no_user_message))
                            }
                            getString(R.string.invalid_password_exception) -> {
                                showToast(getString(R.string.invalid_password_message))
                            }
                        }
                    }
                }
        }
    }

    // 회원가입 이동
    fun startRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    // 토스트 메시지
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}