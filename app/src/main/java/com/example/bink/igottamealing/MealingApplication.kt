package com.example.bink.igottamealing

import android.app.Application
import com.example.bink.igottamealing.di.DaggerMealingComponent
import com.example.bink.igottamealing.di.MealingComponent
import com.example.bink.igottamealing.di.MealingModule

class MealingApplication : Application() {

    lateinit var component: MealingComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerMealingComponent.builder().mealingModule(MealingModule(applicationContext)).build()
    }
}