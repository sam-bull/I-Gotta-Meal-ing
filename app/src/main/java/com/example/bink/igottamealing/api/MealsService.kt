package com.example.bink.igottamealing.api

import com.example.bink.igottamealing.model.Categories
import com.example.bink.igottamealing.model.MealDetailsList
import com.example.bink.igottamealing.model.Meals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealsService {

    companion object {
        // For real API keys, we should have a more secure storage solution
        const val API_KEY = "1"
    }

    @GET("api/json/v1/{apiKey}/categories.php")
    fun getCategories(@Path("apiKey") apiKey: String): Call<Categories>

    @GET("api/json/v1/{apiKey}/filter.php")
    fun getMealsForCategory(@Path("apiKey") apiKey: String, @Query("c") category: String): Call<Meals>

    @GET("api/json/v1/{apiKey}/lookup.php")
    fun getMealDetails(@Path("apiKey") apiKey: String, @Query("i") mealId: String): Call<MealDetailsList>

}
