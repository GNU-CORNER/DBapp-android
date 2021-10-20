package com.example.corner_library.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corner_library.databinding.FragmentEmailAuthBinding

class EmailAuthFragment : Fragment() {
    private lateinit var binding: FragmentEmailAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmailAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.lavMailAuth.playAnimation()

        return binding.root
    }
}