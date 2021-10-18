package com.example.corner_library.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.ButtonBarLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.adapters.CategoryAdapter
import com.example.corner_library.fragment.SearchFragment
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
        val searchBtn = findViewById<Button>(R.id.search_bar)
        categoryAdapter = CategoryAdapter(this, categories)
        rvCategories.adapter = categoryAdapter
        val categoryLayoutManager = object: LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false;
            }
        }

        rvCategories.layoutManager = categoryLayoutManager

        searchBtn.setOnClickListener(View.OnClickListener {
            val searchFragment : SearchFragment = SearchFragment {

            }
            searchFragment.show(supportFragmentManager, searchFragment.tag)
        })
    }

    private fun initData() {

        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "전인혁", subject = "인혁이 관리", logo = R.drawable.logo))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))

        val category1 = Category("기초설계프로젝트 PBL >", projects)
        categories.add(category1)

        val category2 = Category("소프트웨어설계 PBL >", projects)
        categories.add(category2)

        val category3 = Category("전공종합설계 PBL >", projects)
        categories.add(category3)

        val category4 = Category("안드로이드 >", projects)
        categories.add(category4)
    }
}