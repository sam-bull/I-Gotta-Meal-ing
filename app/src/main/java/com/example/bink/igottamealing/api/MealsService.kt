package com.example.bink.igottamealing.api

import com.example.bink.igottamealing.model.Categories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MealsService {

    @GET("api/json/v1/{apiKey}/categories.php")
    fun getCategories(@Path("apiKey") apiKey: String): Call<Categories>
}
