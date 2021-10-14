package com.example.corner_library.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.adapters.CategoryAdapter
import com.example.corner_library.model.Category
import com.example.corner_library.model.Project

class MainViewActivity : AppCompatActivity() {
    lateinit var categoryAdapter: CategoryAdapter

    val categories = ArrayList<Category>()

    val projects = ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)

        initData()

        val rvCategories = findViewById<RecyclerView>(R.id.categories)
        categoryAdapter = CategoryAdapter(this, categories)
        rvCategories.adapter = categoryAdapter
        val categoryLayoutManager = object: LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false;
            }
        }

        rvCategories.layoutManager = categoryLayoutManager


    }

    private fun initData() {

//        titles.add("기초설계프로젝트 PBL >")
//        titles.add("소프트웨어설계 PBL >")
//        titles.add("전공종합설계 PBL >")
//        titles.add("자바스크립트 >")
//        titles.add("안드로이드 >")
//        titles.add("IOS >")

        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))

        val category1 = Category("기초설계프로젝트 PBL >", projects)
        categories.add(category1)

        val category2 = Category("소프트웨어설계 PBL >", projects)
        categories.add(category2)
    }
}