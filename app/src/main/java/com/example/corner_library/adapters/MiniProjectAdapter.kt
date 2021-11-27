package com.example.corner_library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.databinding.ItemRvProjectMiniBinding
import com.example.corner_library.model.Project

class MiniProjectAdapter : ListAdapter<Project, MiniProjectAdapter.ViewHolder>(ProjectDiffUtil) {

    inner class ViewHolder(private val binding: ItemRvProjectMiniBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.project = project
            binding.mainProjectLogo.setImageResource(project.logo)
            binding.executePendingBindings()

            binding.root.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiniProjectAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvProjectMiniBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MiniProjectAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object ProjectDiffUtil : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
            return oldItem.name == newItem.name && oldItem.subject == newItem.subject
        }
    }
}