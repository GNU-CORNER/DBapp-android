package com.example.corner_library.adapters

import android.content.Intent
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.databinding.ItemRvSuggestionBinding
import com.example.corner_library.view.activity.SearchResultActivity

class SearchSuggestionAdapter(private val from: String, private val todo: (String) -> Unit) :
    ListAdapter<SpannableString, SearchSuggestionAdapter.ViewHolder>(SuggestionDiffUtil) {

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

    inner class ViewHolder(private val binding: ItemRvSuggestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(suggestion: SpannableString) {
            binding.suggestion = suggestion
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                if (from == "SearchActivity") {
                    Intent(binding.root.context, SearchResultActivity::class.java).run {
                        putExtra("query", suggestion.toString())
                        ContextCompat.startActivity(binding.root.context, this, null)
                    }
                }

                todo(suggestion.toString())
            }
        }
    }

    companion object SuggestionDiffUtil : DiffUtil.ItemCallback<SpannableString>() {
        override fun areItemsTheSame(oldItem: SpannableString, newItem: SpannableString): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SpannableString,
            newItem: SpannableString
        ): Boolean {
            return oldItem == newItem
        }
    }
}