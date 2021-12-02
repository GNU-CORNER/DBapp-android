package com.example.corner_library.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.databinding.ItemRvSuggestionBinding
import com.example.corner_library.model.Project
import com.example.corner_library.view.activity.SearchResultActivity

class SearchSuggestionAdapter(val clearText: () -> Unit) :
    ListAdapter<Project, SearchSuggestionAdapter.ViewHolder>(ProjectDiffUtil) {

    inner class ViewHolder(private val binding: ItemRvSuggestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.suggestion = project
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                clearText()
                Intent(binding.root.context, SearchResultActivity::class.java).run {
                    putExtra("query", project)
                    ContextCompat.startActivity(binding.root.context, this, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchSuggestionAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvSuggestionBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchSuggestionAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object ProjectDiffUtil : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem.projectName == newItem.projectName && oldItem.subject == newItem.subject
        }
    }
}