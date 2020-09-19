package com.example.bink.igottamealing.adaptor

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.databinding.ViewMealBinding
import com.example.bink.igottamealing.model.Meal
import com.example.bink.igottamealing.view.activity.MealDetailsActivity
import com.squareup.picasso.Picasso

class MealAdapter(private val context: Context, private val resources: Resources, private val meals: List<Meal>) :
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
        val mealName = meals[position].strMeal
        holder.binding.apply {
            imageDescription = resources.getString(R.string.meal_image_description, mealName)
            this.mealName = mealName
        }
        Picasso.get().load(meals[position].strMealThumb)
            .placeholder(R.drawable.ic_image_placeholder_white_24dp).into(holder.imageView)
        val mealId = meals[position].idMeal
        holder.container.setOnClickListener {
            val intent = Intent(context, MealDetailsActivity::class.java).apply {
                putExtra("MEAL_ID", mealId)
            }
            startActivity(context, intent, null)
        }
    }

}
