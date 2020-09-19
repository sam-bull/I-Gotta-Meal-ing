package com.example.bink.igottamealing.di

import android.content.Context
import android.content.res.Resources
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
class MealingModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesMealsService(): MealsService = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(MealsService::class.java)

    @Provides
    @Singleton
    fun providesMainViewModel(resources: Resources, mealsService: MealsService): MainViewModel = MainViewModel(resources, mealsService)

    @Provides
    @Singleton
    fun providesCategoryViewModel(resources: Resources, mealsService: MealsService): CategoryViewModel = CategoryViewModel(resources, mealsService)

    @Provides
    @Singleton
    fun providesResources(): Resources = context.resources

    @Provides
    @Singleton
    fun providesMealViewModel(resources: Resources, mealsService: MealsService): MealDetailsViewModel = MealDetailsViewModel(resources, mealsService)

}