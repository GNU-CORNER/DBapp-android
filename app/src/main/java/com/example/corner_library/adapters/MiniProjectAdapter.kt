package com.example.corner_library.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.databinding.ItemRvProjectMiniBinding
import com.example.corner_library.databinding.ProjectCardBinding
import com.example.corner_library.model.Project
import com.example.corner_library.view.activity.ProjectDetailActivity

class MiniProjectAdapter(val pageName: String) : ListAdapter<Project, MiniProjectAdapter.ViewHolder>(
    ProjectDiffUtil
) {

    inner class ViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            if(pageName == "main"){
                binding as ItemRvProjectMiniBinding
                binding.project = project
                binding.projectLogo.setImageResource(project.logo)
            }
            else{
                binding as ProjectCardBinding
                binding.project = project
                binding.projectLogo.setImageResource(project.logo)
            }


            binding.executePendingBindings()

            binding.root.setOnClickListener {
                Log.d("디버그", project.toString())

                val intent = Intent(binding.root.context, ProjectDetailActivity::class.java)
                intent.putExtra("project", project)
                startActivity(binding.root.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiniProjectAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = if(pageName == "main")
            ItemRvProjectMiniBinding.inflate(layoutInflater, parent, false)
        else
            ProjectCardBinding.inflate(layoutInflater, parent, false)

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