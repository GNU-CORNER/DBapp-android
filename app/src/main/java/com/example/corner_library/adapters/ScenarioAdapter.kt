package com.example.corner_library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.databinding.ItemRvScenarioBinding

class ScenarioAdapter : ListAdapter<Int, ScenarioAdapter.ViewHolder>(ScenarioDiffUtil) {

    inner class ViewHolder(private val binding: ItemRvScenarioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image : Int) {
            binding.scenario.setImageResource(image)

            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRvScenarioBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object ScenarioDiffUtil : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }
}