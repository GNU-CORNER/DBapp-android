package com.example.corner_library.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.adapters.CategoryAdapter

class MainViewActivity : AppCompatActivity() {
    lateinit var categoryAdapter: CategoryAdapter
    val titles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_view)

        val rv_categories = findViewById<RecyclerView>(R.id.categories)

        initRecycler(rv_categories)
    }

    private fun initRecycler(rv_categories: RecyclerView) {
        categoryAdapter = CategoryAdapter(this)
        rv_categories.adapter = categoryAdapter

        titles.add("기초설계프로젝트 PBL")
        titles.add("소프트웨어설계 PBL")
        titles.add("전공종합설계 PBL")
        titles.add("자바스크립트")
        titles.add("안드로이드")
        titles.add("IOS")

        categoryAdapter.titles = titles
        categoryAdapter.notifyDataSetChanged()

    }
}