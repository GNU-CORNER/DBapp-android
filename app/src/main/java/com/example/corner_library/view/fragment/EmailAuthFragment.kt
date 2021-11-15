package com.example.corner_library.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corner_library.databinding.FragmentEmailAuthBinding
import com.example.corner_library.view.activity.RegisterActivity

class EmailAuthFragment : Fragment() {
    private lateinit var binding: FragmentEmailAuthBinding
    private lateinit var activity: RegisterActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmailAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        activity = requireActivity() as RegisterActivity

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false) // 뒤로가기 버튼 비활성화
    }

    fun onButtonClick() {
        activity.finish()
    }
}