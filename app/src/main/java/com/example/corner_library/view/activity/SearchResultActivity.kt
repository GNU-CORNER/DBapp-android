package com.example.corner_library.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corner_library.R
import com.example.corner_library.adapters.MiniProjectAdapter
import com.example.corner_library.databinding.ActivitySearchResultBinding
import com.example.corner_library.viewmodel.MainViewModel

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        setBinding()
        setRecyclerView()


        binding.toolbar.setNavigationIcon(R.drawable.back_icon)

//        initData()

        binding.toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })

        // 검색 입력창 관련 메소드
        binding.searchEditText.setOnKeyListener { view, keyCode, keyEvent ->
            var handled = false
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            // 엔터 또는 키보드 내 완료 버튼 눌렀을때 행동
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                imm.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
                val intent = Intent(this, SearchResultActivity::class.java)
                finish()
                startActivity(intent)
//                Toast.makeText(this, "검색 입력 완료", Toast.LENGTH_SHORT).show()
            }

            handled
        }

        binding.dropdownSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (binding.dropdownSort.getItemAtPosition(position)){
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



    private fun setBinding() {
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.view = this
        binding.viewModel = viewModel
        setContentView(binding.root)
    }

    private fun setRecyclerView() {
        binding.projects.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = MiniProjectAdapter("search")
            setHasFixedSize(true)
        }
    }

//    private fun initData() {
//
//        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
//        projects.add(Project(name = "전인혁", subject = "인혁이 관리", logo = R.drawable.logo))
//        projects.add(Project(name = "Local Sharing", subject = "밥집 관리", logo = R.drawable.logo3))
//        projects.add(Project(name = "신선고", subject = "냉장고 관리", logo = R.drawable.logo2))
//        projects.add(Project(name = "앱앱고", subject = "앱 관리", logo = R.drawable.logo))
//        projects.add(Project(name = "Local Sharing", subject = "밥집 관리", logo = R.drawable.logo3))
//        projects.add(Project(name = "이거시 뭐시여", subject = "몰라 관리", logo = R.drawable.logo2))
//
//    }
}