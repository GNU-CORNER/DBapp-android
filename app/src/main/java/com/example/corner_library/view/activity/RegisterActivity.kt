package com.example.corner_library.view.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.corner_library.R
import com.example.corner_library.databinding.ActivityRegisterBinding
import com.example.corner_library.view.fragment.EmailAuthFragment
import com.example.corner_library.view.fragment.EmailInputFragment
import com.example.corner_library.view.fragment.NameInputFragment
import com.example.corner_library.view.fragment.PasswordInputFragment
import com.example.corner_library.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private val keyboardController by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        setSupportActionBar(binding.toolbar)
        setObserver()

        supportActionBar!!.displayOptions = ActionBar.DISPLAY_HOME_AS_UP
    }

    override fun onBackPressed() {
        when (supportFragmentManager.backStackEntryCount) {
            1 -> {
                finish()
            }
            RegisterViewModel.NUM_PAGES -> {

            }
            else -> {
                supportFragmentManager.popBackStackImmediate()
                viewModel.prevPage()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack()
                viewModel.prevPage()
            } else {
                finish()
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.lifecycleOwner = this
        binding.view = this
        binding.viewModel = viewModel
    }

    private fun setObserver() {
        // 페이지 변경에 따른 프래그먼트 변경
        viewModel.currentPage.observe(this, Observer {
            if (it != supportFragmentManager.backStackEntryCount) {
                val fragment = when (it) {
                    1 -> NameInputFragment()
                    2 -> EmailInputFragment()
                    3 -> PasswordInputFragment()
                    else -> EmailAuthFragment()
                }

                supportFragmentManager.beginTransaction().apply {
                    setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left,
                        R.anim.enter_from_left,
                        R.anim.exit_to_right
                    )
                    replace(R.id.fl_container, fragment)
                    addToBackStack(null)
                    commit()
                }
            }
        })
    }

    // 현재 프래그먼트의 onButtonClick 메소드 실행
    fun onButtonClick() {
        val fragment = supportFragmentManager.fragments.last()

        when (viewModel.currentPage.value) {
            1 -> (fragment as NameInputFragment).onButtonClick()
            2 -> (fragment as EmailInputFragment).onButtonClick()
            3 -> (fragment as PasswordInputFragment).onButtonClick()
            4 -> (fragment as EmailAuthFragment).onButtonClick()
        }
    }

    // 키보드 보이기
    fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        keyboardController.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    // 키보드 숨김
    fun hideKeyboard(editText: EditText) {
        keyboardController.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}