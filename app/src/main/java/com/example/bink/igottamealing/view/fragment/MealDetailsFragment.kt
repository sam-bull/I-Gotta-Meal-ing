package com.example.bink.igottamealing.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bink.igottamealing.MealingApplication
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.adapter.IngredientAdapter
import com.example.bink.igottamealing.adapter.InstructionAdapter
import com.example.bink.igottamealing.databinding.FragmentMealDetailsBinding
import com.example.bink.igottamealing.viewmodel.MealDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_meal_details.*
import javax.inject.Inject

class MealDetailsFragment : Fragment() {

    companion object {
        fun newInstance() =
            MealDetailsFragment()
    }

    @Inject
    lateinit var viewModel: MealDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as MealingApplication).component.inject(this)
        val binding: FragmentMealDetailsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_meal_details, container, false
            )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.mealId = activity?.intent?.getStringExtra("MEAL_ID") ?: ""
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ingredients_recyclerview.layoutManager = LinearLayoutManager(context)
        ingredients_recyclerview.adapter = IngredientAdapter(viewModel.ingredients)

        instructions_recyclerview.layoutManager = LinearLayoutManager(context)
        instructions_recyclerview.adapter = InstructionAdapter(viewModel.instructions)

        viewModel.image.observe(viewLifecycleOwner, Observer { url ->
            Picasso.get().load(url).placeholder(R.drawable.ic_image_placeholder_white_24dp)
                .into(meal_image)
        })

        viewModel.detailsLoaded.observe(viewLifecycleOwner, Observer { success ->
            meal_details_view_flipper.displayedChild = if (success) 1 else 2
            (ingredients_recyclerview.adapter as IngredientAdapter).notifyDataSetChanged()
            (instructions_recyclerview.adapter as InstructionAdapter).notifyDataSetChanged()
            ingredients_recyclerview.scheduleLayoutAnimation()
            instructions_recyclerview.scheduleLayoutAnimation()
        })

        viewModel.onViewCreated()
    }
}
