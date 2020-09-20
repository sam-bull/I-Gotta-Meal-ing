package com.example.bink.igottamealing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bink.igottamealing.databinding.ViewIngredientBinding
import com.example.bink.igottamealing.model.Ingredient
import java.util.*

class IngredientAdapter (private val ingredients: List<Ingredient>) :
        RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

        class IngredientViewHolder(val binding: ViewIngredientBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder =
            IngredientViewHolder(
                ViewIngredientBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun getItemCount() = ingredients.size

        override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
            holder.binding.apply {
                ingredient = ingredients[position].toString()
                isLight = position % 2 == 0
            }
        }

}