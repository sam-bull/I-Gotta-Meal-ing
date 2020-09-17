package com.example.bink.igottamealing.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bink.igottamealing.databinding.ViewCategoryBinding

class CategoryAdaptor(private val categories: List<String>) :
    RecyclerView.Adapter<CategoryAdaptor.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: ViewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ViewCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            categoryName = categories[position]
        }
    }

}
