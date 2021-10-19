package com.example.corner_library.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.adapters.SearchResultProjectAdapter
import com.example.corner_library.model.Project

class SearchResultActivity : AppCompatActivity() {
    lateinit var projectAdapter: SearchResultProjectAdapter
    private val projects = ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val toolbar = findViewById<Toolbar>(R.id.search_result_toolbar)
        val editText = findViewById<EditText>(R.id.search_edit_text)
        val rvProject = findViewById<RecyclerView>(R.id.projects)
        val spinner = findViewById<Spinner>(R.id.dropdown_sort)

        toolbar.setNavigationIcon(R.drawable.back_icon)
        toolbar.navigationIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
            ContextCompat.getColor(this, R.color.personal_color), BlendModeCompat.SRC_ATOP)

        initData()

        projectAdapter = SearchResultProjectAdapter(projects)
        rvProject.adapter = projectAdapter

        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

        // 검색 입력창 관련 메소드
        editText.setOnKeyListener { view, keyCode, keyEvent ->
            var handled = false
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            // 엔터 또는 키보드 내 완료 버튼 눌렀을때 행동
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                val intent = Intent(this, SearchResultActivity::class.java)
                finish()
                startActivity(intent)
//                Toast.makeText(this, "검색 입력 완료", Toast.LENGTH_SHORT).show()
            }

            handled
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (spinner.getItemAtPosition(position)){
                    "최신순" -> {
                        Toast.makeText(parent?.context, "최신순", Toast.LENGTH_SHORT).show()
                    }
                    "오래된순" -> {
                        Toast.makeText(parent?.context, "오래된순", Toast.LENGTH_SHORT).show()
                    }
                    "오름차순" -> {
                        Toast.makeText(parent?.context, "오름차순", Toast.LENGTH_SHORT).show()
                    }
                    "내림차순" -> {
                        Toast.makeText(parent?.context, "내림차순", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun initData() {

        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "전인혁", subject = "인혁이 관리", logo = R.drawable.logo))
        projects.add(Project(name = "Local Sharing", subject = "밥집 관리", logo = R.drawable.logo3))
        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
        projects.add(Project(name = "앱앱고", subject = "앱 관리", logo = R.drawable.logo))
        projects.add(Project(name = "Local Sharing", subject = "밥집 관리", logo = R.drawable.logo3))
        projects.add(Project(name = "이거시 뭐시여", subject = "몰라 관리", logo = R.drawable.logo2))

    }
}