package com.example.corner_library.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corner_library.adapters.SearchSuggestionAdapter
import com.example.corner_library.databinding.ActivitySearchBinding
import com.example.corner_library.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        setActionBar()
        showKeyboard()
        setSearchEditText()
        setRecyclerView()

        binding.llContainer.setOnClickListener {
            hideKeyboard()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setBinding() {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
    }

    private fun setActionBar() {
        setSupportActionBar(binding.searchToolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setRecyclerView() {
        binding.rvSuggestions.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = SearchSuggestionAdapter { finish() }
        }
    }

    // 검색 입력창 관련 설정
    private fun setSearchEditText() {
        binding.etSearch.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) { // 키보드의 다음 버튼 클릭 시
                    val intent = Intent(context, SearchResultActivity::class.java)
                    intent.putExtra("query", this.text)
                    startActivity(intent)
                    finish()

                    return@setOnEditorActionListener true
                }

                return@setOnEditorActionListener false
            }
            // 텍스트 입력 변경 시
            doOnTextChanged { query, _, _, _ ->
                query?.let { viewModel.filterSuggestions(query.toString()) }
            }
        }
    }

    // 키보드 보이기
    private fun showKeyboard() {
        binding.etSearch.requestFocus()
        inputMethodManager.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    // 키보드 숨김
    private fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }
}