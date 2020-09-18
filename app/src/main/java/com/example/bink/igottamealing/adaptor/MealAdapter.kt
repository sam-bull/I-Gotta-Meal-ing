package com.example.bink.igottamealing.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.databinding.ViewMealBinding
import com.example.bink.igottamealing.model.Meal
import com.squareup.picasso.Picasso

class MealAdapter(private val meals: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    class MealViewHolder(val binding: ViewMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val container = binding.mealContainer
        val imageView = binding.mealImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder =
        MealViewHolder(
            ViewMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = meals.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.binding.apply {
            mealName = meals[position].strMeal
        }
        Picasso.get().load(meals[position].strMealThumb).placeholder(R.drawable.ic_image_placeholder_white_24dp).into(holder.imageView)
        holder.container.setOnClickListener { }
    }

}
