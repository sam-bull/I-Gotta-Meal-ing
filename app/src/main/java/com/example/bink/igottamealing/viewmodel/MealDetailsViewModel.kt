package com.example.bink.igottamealing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Ingredient
import com.example.bink.igottamealing.model.MealDetails
import com.example.bink.igottamealing.model.MealDetailsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MealDetailsViewModel @Inject constructor(private val mealsService: MealsService) :
    ViewModel() {

    lateinit var mealId: String

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    val ingredients = mutableListOf<Ingredient>()

    val instructions = mutableListOf<String>()

    fun onViewCreated() {
        mealsService.getMealDetails(MealsService.API_KEY, mealId)
            .enqueue(object : Callback<MealDetailsList> {
                override fun onResponse(
                    call: Call<MealDetailsList>,
                    response: Response<MealDetailsList>
                ) {
                    if (response.isSuccessful && response.body()?.meals?.isNotEmpty() == true) {
                        response.body()?.meals?.let {
                            updateMealDetails(it[0])
                        }
                    } else {
                        _title.postValue("error")
                        println(response.message())
                    }
                }

                override fun onFailure(call: Call<MealDetailsList>, t: Throwable) {
                    _title.postValue("${t.message}")
                }
            })
    }

    private fun updateMealDetails(mealDetails: MealDetails) {
        _image.postValue(mealDetails.strMealThumb)
        _title.postValue(mealDetails.strMeal)
        ingredients.addAll(mealDetails.getIngredients())
        instructions.addAll(mealDetails.strInstructions.split("\n"))
    }
}