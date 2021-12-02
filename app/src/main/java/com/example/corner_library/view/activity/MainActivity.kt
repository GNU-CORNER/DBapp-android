package com.example.corner_library.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corner_library.adapters.CategoryAdapter
import com.example.corner_library.databinding.ActivityMainBinding
import com.example.corner_library.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        setRecyclerView()
    }

    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.view = this
        binding.viewModel = viewModel
        setContentView(binding.root)
    }

    private fun setRecyclerView() {
        binding.categories.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = CategoryAdapter()
            setHasFixedSize(true)
        }
    }

    fun startSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }
}