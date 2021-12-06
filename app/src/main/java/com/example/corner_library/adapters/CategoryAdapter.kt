package com.example.corner_library.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.databinding.ItemRvCategoryBinding
import com.example.corner_library.data.model.Category
import com.example.corner_library.view.activity.SearchResultActivity

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffUtil) {
//    위에 매개 변수 대신 이런 방법도 있음
//    var category = ArrayList<Category>();

    inner class ViewHolder(private val binding: ItemRvCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.category = category
            binding.miniProjects.apply {
                layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = MiniProjectAdapter("main")
                setHasFixedSize(true)
            }
            binding.executePendingBindings()

            binding.moreBtn.setOnClickListener {
                Intent(binding.root.context, SearchResultActivity::class.java).run {
                    putExtra("Category", category)
                    startActivity(binding.root.context, this, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvCategoryBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_rv_category
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.title == newItem.title && oldItem.projectList == newItem.projectList
        }
    }
}