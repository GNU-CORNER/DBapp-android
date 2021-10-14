package com.example.corner_library.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.corner_library.R
import com.example.corner_library.model.Project

class MiniProjectAdapter(private val context: ArrayList<Project>) : RecyclerView.Adapter<MiniProjectAdapter.ViewHolder>() {
    var projects = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var v: View = view;

        var logo: ImageView = itemView.findViewById(R.id.main_project_logo)
        var name: TextView = itemView.findViewById(R.id.main_project_name)
        var subject: TextView = itemView.findViewById(R.id.main_project_subject)

        fun bind(listener: View.OnClickListener) {
            v.setOnClickListener(listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_mini_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "${projects[position].name}", Toast.LENGTH_SHORT).show()
        }
        holder.name.setText(projects[position].name)
        holder.subject.setText(projects[position].subject)
        holder.logo.setImageResource(projects[position].logo)
        holder.bind(listener)
    }

    override fun getItemCount(): Int = projects.size
}