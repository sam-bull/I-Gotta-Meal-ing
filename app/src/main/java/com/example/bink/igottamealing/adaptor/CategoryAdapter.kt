package com.example.bink.igottamealing.adaptor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bink.igottamealing.databinding.ViewCategoryBinding
import com.example.bink.igottamealing.model.Category
import com.example.bink.igottamealing.view.CategoryActivity
import com.squareup.picasso.Picasso

class CategoryAdapter(private val context: Context, private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(val binding: ViewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val container = binding.categoryContainer
        val imageView = binding.categoryImage
    }

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
            categoryName = categories[position].strCategory
        }
        Picasso.get().load(categories[position].strCategoryThumb).into(holder.imageView)
        val name = categories[position].strCategory
        holder.container.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java).apply {
                putExtra("CATEGORY", name)
            }
            startActivity(context, intent, null)
        }
    }

}