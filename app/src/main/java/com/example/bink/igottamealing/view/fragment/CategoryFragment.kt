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
import com.example.bink.igottamealing.adaptor.MealAdapter
import com.example.bink.igottamealing.databinding.FragmentCategoryBinding
import com.example.bink.igottamealing.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import javax.inject.Inject

class CategoryFragment : Fragment() {

    companion object {
        fun newInstance() =
            CategoryFragment()
    }

    @Inject
    lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as MealingApplication).component.inject(this)
        val binding: FragmentCategoryBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_category, container, false
            )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.category = activity?.intent?.getStringExtra("CATEGORY") ?: ""
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        meals_recyclerview.layoutManager = LinearLayoutManager(context)
        meals_recyclerview.adapter = context?.let { MealAdapter(it, resources, viewModel.meals) }

        viewModel.mealsLoaded.observe(viewLifecycleOwner, Observer { success ->
            meals_view_flipper.displayedChild = if (success) 1 else 2
            (meals_recyclerview.adapter as MealAdapter).notifyDataSetChanged()
        })

        viewModel.onViewCreated()
    }
}
