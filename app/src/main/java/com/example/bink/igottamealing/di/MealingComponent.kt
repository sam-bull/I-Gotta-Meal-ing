package com.example.bink.igottamealing.di

import com.example.bink.igottamealing.view.CategoryFragment
import com.example.bink.igottamealing.view.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MealingModule::class])
interface MealingComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(categoryFragment: CategoryFragment)

}