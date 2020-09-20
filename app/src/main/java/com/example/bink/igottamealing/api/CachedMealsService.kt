package com.example.bink.igottamealing.api

import com.example.bink.igottamealing.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachedMealsService @Inject constructor(private val mealsService: MealsService) {

    private val categoriesCache = mutableListOf<Category>()
    private val mealsCache = mutableMapOf<String, List<Meal>>()
    private val mealDetailsCache = mutableMapOf<String, MealDetails>()

    fun getCategories(
        onSuccess: (meals: List<Category>?) -> Unit,
        onError: (errorMessage: String?) -> Unit
    ) {
        if (categoriesCache.isNotEmpty()) {
            onSuccess(categoriesCache)
            return
        }
        mealsService.getCategories(MealsService.API_KEY).enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful) {
                    val categories = response.body()?.categories
                    categories?.let { categoriesCache.addAll(it) }
                    onSuccess(categories)
                } else {
                    onError(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                onError(t.message)
            }
        })
    }

    fun getMealsForCategory(
        category: String,
        onSuccess: (meals: List<Meal>?) -> Unit,
        onError: (errorMessage: String?) -> Unit
    ) {
        if (mealsCache.containsKey(category)) {
            onSuccess(mealsCache[category])
            return
        }
        mealsService.getMealsForCategory(MealsService.API_KEY, category)
            .enqueue(object : Callback<Meals> {
                override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                    if (response.isSuccessful) {
                        val meals = response.body()?.meals
                        meals?.let {mealsCache[category] = it }
                        onSuccess(meals)
                    } else {
                        onError(response.errorBody()?.string())
                    }
                }

                override fun onFailure(call: Call<Meals>, t: Throwable) {
                    onError(t.message)
                }
            })
    }

    fun getMealDetails(
        mealId: String,
        onSuccess: (meals: MealDetails?) -> Unit,
        onError: (errorMessage: String?) -> Unit
    ) {
        if (mealDetailsCache.containsKey(mealId)) {
            onSuccess(mealDetailsCache[mealId])
            return
        }
        mealsService.getMealDetails(MealsService.API_KEY, mealId)
            .enqueue(object : Callback<MealDetailsList> {
                override fun onResponse(
                    call: Call<MealDetailsList>,
                    response: Response<MealDetailsList>
                ) {
                    if (response.isSuccessful && response.body()?.meals?.isNotEmpty() == true) {
                        val mealDetails = response.body()?.meals?.get(0)
                        mealDetails?.let { mealDetailsCache[mealId] = it }
                        onSuccess(mealDetails)
                    } else {
                        onError(response.errorBody()?.string())
                    }
                }

                override fun onFailure(call: Call<MealDetailsList>, t: Throwable) {
                    onError(t.message)
                }
            })
    }
}