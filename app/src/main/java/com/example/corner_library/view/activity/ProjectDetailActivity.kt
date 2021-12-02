package com.example.corner_library.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.corner_library.R
import com.example.corner_library.databinding.ActivityProjectDetailBinding
import com.example.corner_library.databinding.ActivitySearchResultBinding
import com.example.corner_library.model.Project

class ProjectDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProjectDetailBinding
    private lateinit var project: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        project = intent.getParcelableExtra<Project>("project")!!

        setBinding()
        setActionBar()
        setProjectInfo()

    }

    private fun setBinding() {
        binding = ActivityProjectDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.view = this
        setContentView(binding.root)
    }

    private fun setActionBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = ""
        binding.toolbar.setNavigationIcon(R.drawable.back_icon)
        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

    }

    private fun setProjectInfo() {
        binding.projectLogo.setImageResource(project?.logo!!)
        binding.projectName.text = project?.projectName
    }
}