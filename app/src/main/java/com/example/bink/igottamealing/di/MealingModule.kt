package com.example.bink.igottamealing.di

import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.viewmodel.CategoryViewModel
import com.example.bink.igottamealing.viewmodel.MainViewModel
import com.example.bink.igottamealing.viewmodel.MealDetailsViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MealingModule {

    @Provides
    @Singleton
    fun providesMealsService(): MealsService = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(MealsService::class.java)


    @Provides
    @Singleton
    fun providesMainViewModel(mealsService: MealsService): MainViewModel = MainViewModel(mealsService)

    @Provides
    @Singleton
    fun providesCategoryViewModel(mealsService: MealsService): CategoryViewModel = CategoryViewModel(mealsService)

    @Provides
    @Singleton
    fun providesMealViewModel(mealsService: MealsService): MealDetailsViewModel = MealDetailsViewModel(mealsService)
}