package com.example.corner_library.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.corner_library.R
import com.example.corner_library.databinding.ActivityLoginBinding

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
//        FirebaseAuth
//            .getInstance()
//            .signInWithEmailAndPassword(이메일, 비밀번호)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified!!) {
//                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "이메일 인증 필요", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Log.i("LoginActivity", task.exception?.message!!)
//                }
//            }
    }

    // 회원가입 이동
    fun startRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}