package com.example.bink.igottamealing.di

import com.example.bink.igottamealing.view.fragment.CategoryFragment
import com.example.bink.igottamealing.view.fragment.MainFragment
import com.example.bink.igottamealing.view.fragment.MealDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MealingModule::class])
interface MealingComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(categoryFragment: CategoryFragment)

    fun inject(mealDetailsFragment: MealDetailsFragment)

}