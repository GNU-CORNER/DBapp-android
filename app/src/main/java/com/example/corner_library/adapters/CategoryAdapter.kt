package com.example.corner_library.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R

class CategoryAdapter(private val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var titles = ArrayList<String>();

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var v: View = view;
        private var titleName: TextView = itemView.findViewById(R.id.category_title)

        fun bind(listener: View.OnClickListener, item: String) {
            titleName.text = item
            v.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_recycler_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "${titles[position]}", Toast.LENGTH_SHORT).show()
        }
        holder.bind(listener, titles[position])
    }

    override fun getItemCount(): Int = titles.size


}