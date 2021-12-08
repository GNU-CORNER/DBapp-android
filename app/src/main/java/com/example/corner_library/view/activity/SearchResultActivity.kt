package com.example.corner_library.view.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corner_library.R
import com.example.corner_library.adapters.MiniProjectAdapter
import com.example.corner_library.adapters.SearchSuggestionAdapter
import com.example.corner_library.data.model.Category
import com.example.corner_library.databinding.ActivitySearchResultBinding
import com.example.corner_library.viewmodel.SearchResultViewModel
import com.example.corner_library.viewmodel.ViewModelFactory

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var viewModel: SearchResultViewModel
    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(
                intent.getStringExtra("query"),
                intent.getParcelableExtra<Category>("Category")
            )
        ).get(SearchResultViewModel::class.java)

        setBinding()
        setRecyclerView()
        setSearchEditText()

        binding.toolbar.setNavigationIcon(R.drawable.back_icon)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.dropdownSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (binding.dropdownSort.getItemAtPosition(position)) {
                    "최신순" -> {

                    }
                    "오래된순" -> {

                    }
                    "오름차순" -> {

                    }
                    "내림차순" -> {

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
        binding.viewModel = viewModel
        setContentView(binding.root)
    }

    private fun setRecyclerView() {
        binding.projects.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = MiniProjectAdapter("search")
            itemAnimator = null
            setHasFixedSize(true)
        }

        binding.rvSuggestions.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter =
                SearchSuggestionAdapter("SearchResultActivity") { query ->
                    viewModel.searchProject(query)
                    hideKeyboard()
                }
        }
    }

    private fun setSearchEditText() {
        // 검색 입력창 관련 메소드
        binding.searchEditText.apply {
            setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {   // 키보드의 검색 버튼 클릭 시
                    if (view.text.isNotEmpty()) viewModel.searchProject(view.text.toString())
                    hideKeyboard()

                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }
            // 텍스트 입력 변경 시
            doOnTextChanged { query, _, _, _ ->
                query?.let { viewModel.searchSuggestion(query.toString()) }
            }
        }
    }

    // 키보드 숨김
    private fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(binding.searchEditText.windowToken, 0)
        binding.searchEditText.clearFocus()
    }
}