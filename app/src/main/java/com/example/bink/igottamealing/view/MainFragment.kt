package com.example.bink.igottamealing.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.adaptor.CategoryAdaptor
import com.example.bink.igottamealing.databinding.FragmentMainBinding
import com.example.bink.igottamealing.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMainBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main, container, false
            )
        binding.lifecycleOwner = this
        viewModel = MainViewModel()
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories_recyclerview.layoutManager = LinearLayoutManager(context)
        categories_recyclerview.adapter = CategoryAdaptor(viewModel.categories)

        viewModel.categoriesLoaded.observe(viewLifecycleOwner, Observer { success ->
            categories_view_flipper.displayedChild = if (success) 1 else 2
            (categories_recyclerview.adapter as CategoryAdaptor).notifyDataSetChanged()

        })

        viewModel.onViewCreated()
    }

}
