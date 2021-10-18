package com.example.bookstore.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.google.android.material.textview.MaterialTextView

class HorizontalSectionAdapter(private val sectionTitle: String, private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.adapter_horizontal_section, parent, false)
        return HorizontalSectionViewHolder(view)
    }

    override fun getItemCount(): Int  = 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HorizontalSectionViewHolder).bind(sectionTitle, adapter)
    }

    class HorizontalSectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(sectionTitle: String, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            itemView.findViewById<MaterialTextView>(R.id.materialtextview_section_title).text = sectionTitle
            itemView.findViewById<RecyclerView>(R.id.recyclerview_horizontal_section).adapter = adapter
        }
    }
}