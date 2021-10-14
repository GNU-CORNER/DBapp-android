package com.example.corner_library.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.model.Category

class CategoryAdapter(private val context: Context, val category: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
//    var category = ArrayList<Category>();

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var v: View = view;
        var titleName: TextView = itemView.findViewById(R.id.category_title)
        var rvProject: RecyclerView = itemView.findViewById(R.id.mini_projects)

        fun bind(listener: View.OnClickListener) {
            v.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_recycler_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val projectAdapter = MiniProjectAdapter(category[position].projectList)

        holder.titleName.setText(category[position].title)
        holder.rvProject.adapter = projectAdapter

        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, category[position].title, Toast.LENGTH_SHORT).show()
        }
        holder.bind(listener)
    }

    override fun getItemCount(): Int = category.size


}