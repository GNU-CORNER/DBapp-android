package com.example.corner_library.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.model.Category
import com.example.corner_library.view.activity.SearchResultActivity

class CategoryAdapter(private val context: Context, val category: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
//    위에 매개 변수 대신 이런 방법도 있음
//    var category = ArrayList<Category>();

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleName: TextView = itemView.findViewById(R.id.category_title)
        private var moreBtn: TextView = itemView.findViewById(R.id.more_btn)
        var rvProject: RecyclerView = itemView.findViewById(R.id.mini_projects)

        fun bind(listener: View.OnClickListener) {
            moreBtn.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_recycler_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val projectAdapter = MiniProjectAdapter(category[position].projectList)

        holder.titleName.text = category[position].title
        holder.rvProject.adapter = projectAdapter

        val listener = View.OnClickListener { it ->
            val intent = Intent(context, SearchResultActivity::class.java)
            startActivity(context, intent, null)
        }
        holder.bind(listener)
    }

    override fun getItemCount(): Int = category.size


}