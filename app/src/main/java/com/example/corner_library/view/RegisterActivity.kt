package com.example.corner_library.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.findFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.corner_library.R
import com.example.corner_library.databinding.ActivityRegisterBinding
import com.example.corner_library.viewmodel.RegisterViewModel
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var viewPagerAdapter: FragmentStateAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        setViewPager()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewModel.prevPage()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (viewPager.currentItem > 0) {
                viewModel.prevPage()
            } else {
                finish()
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.lifecycleOwner = this
        binding.view = this
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.apply {
            displayOptions = ActionBar.DISPLAY_HOME_AS_UP
        }
    }

    private fun setViewPager() {
        viewPager = binding.vpContainer
        viewPagerAdapter = ViewPagerAdapter(this)

        viewPager.apply {
            adapter = viewPagerAdapter
            isUserInputEnabled = false
            offscreenPageLimit = 1
        }
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = RegisterViewModel.NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> NameInputFragment()
                1 -> EmailInputFragment()
                2 -> PasswordInputFragment()
                else -> EmailAuthFragment()
            }
        }
    }
}