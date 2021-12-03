package com.example.corner_library.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corner_library.R
import com.example.corner_library.adapters.CategoryAdapter
import com.example.corner_library.adapters.ScenarioAdapter
import com.example.corner_library.databinding.ActivityProjectDetailBinding
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
        binding.project = project
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
        // logo
        binding.projectLogo.setImageResource(project.logo)
        // project name
        binding.projectName.text = project.projectName
        // team name
        binding.teamName.text = project.teamName
        // members
        binding.members.text = ("(" + project.members + ")")
        // tags
        binding.tags.text = getTagsString()
        // subject
        binding.projectSubject.text = project.subject
        // description
        binding.projectDescription.text = project.description
        // scenario
        setRecyclerView()
    }

    private fun getTagsString(): String {
        var tags : String
        tags = project.tags.map { "#" + it.name }.joinToString(" ", "", "")

        return tags
    }

    private fun setRecyclerView() {
        binding.projectScenarios.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = ScenarioAdapter()
            setHasFixedSize(true)
        }
    }
}