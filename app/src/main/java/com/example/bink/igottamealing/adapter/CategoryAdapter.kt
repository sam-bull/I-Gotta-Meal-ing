package com.example.bink.igottamealing.adapter

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.databinding.ViewCategoryBinding
import com.example.bink.igottamealing.model.Category
import com.example.bink.igottamealing.view.activity.CategoryActivity
import com.squareup.picasso.Picasso

class CategoryAdapter(private val context: Context, private val resources: Resources, private val categories: List<Category>) :
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
        val name = categories[position].strCategory
        holder.binding.apply {
            imageDescription = resources.getString(R.string.category_image_description, name)
            categoryName = name
        }
        Picasso.get().load(categories[position].strCategoryThumb).placeholder(R.drawable.ic_image_placeholder_white_24dp).into(holder.imageView)
        holder.container.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java).apply {
                putExtra("CATEGORY", name)
            }
            startActivity(context, intent, null)
        }
    }

}
