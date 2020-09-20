package com.example.bink.igottamealing.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bink.igottamealing.MealingApplication
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.adapter.CategoryAdapter
import com.example.bink.igottamealing.databinding.FragmentMainBinding
import com.example.bink.igottamealing.layoutmanager.GridAutofitLayoutManager
import com.example.bink.igottamealing.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as MealingApplication).component.inject(this)
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main, container, false
            )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories_swipe_refresh_layout.setOnRefreshListener {
            viewModel.onRefresh()
        }
        categories_recyclerview.layoutManager = context?.let { GridAutofitLayoutManager(it, resources.getDimension(R.dimen.category_item_min_width_dp)) }
        categories_recyclerview.adapter = context?.let { CategoryAdapter(it, resources, viewModel.categories) }

        viewModel.categoriesLoaded.observe(viewLifecycleOwner, Observer { success ->
            categories_view_flipper.displayedChild = if (success) 1 else 2
            (categories_recyclerview.adapter as CategoryAdapter).notifyDataSetChanged()
            categories_swipe_refresh_layout.isRefreshing = false
            categories_recyclerview.scheduleLayoutAnimation()
        })

        viewModel.onViewCreated()
    }

}
