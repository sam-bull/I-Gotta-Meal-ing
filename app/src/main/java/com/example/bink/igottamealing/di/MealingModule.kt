package com.example.bink.igottamealing.di

import android.content.Context
import android.content.res.Resources
import com.example.bink.igottamealing.api.CachedMealsService
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
    fun providesCachedMealsService(mealsService: MealsService): CachedMealsService = CachedMealsService(mealsService)

    @Provides
    @Singleton
    fun providesMainViewModel(resources: Resources, cachedMealsService: CachedMealsService): MainViewModel = MainViewModel(resources, cachedMealsService)

    @Provides
    @Singleton
    fun providesCategoryViewModel(resources: Resources, cachedMealsService: CachedMealsService): CategoryViewModel = CategoryViewModel(resources, cachedMealsService)

    @Provides
    @Singleton
    fun providesResources(): Resources = context.resources

    @Provides
    @Singleton
    fun providesMealViewModel(resources: Resources, cachedMealsService: CachedMealsService): MealDetailsViewModel = MealDetailsViewModel(resources, cachedMealsService)

}