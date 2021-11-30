package com.example.corner_library.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.corner_library.R
import com.example.corner_library.databinding.ActivityProjectDetailBinding
import com.example.corner_library.databinding.ActivitySearchResultBinding
import com.example.corner_library.model.Project

class ProjectDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProjectDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        setBinding()

        var project = intent.getParcelableExtra<Project>("project")

        binding.text.text = project?.name
    }

    private fun setBinding() {
        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.view = this
        setContentView(binding.root)
    }
}