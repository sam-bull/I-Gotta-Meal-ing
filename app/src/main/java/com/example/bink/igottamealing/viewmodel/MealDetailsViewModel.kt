package com.example.bink.igottamealing.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.api.CachedMealsService
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Ingredient
import com.example.bink.igottamealing.model.MealDetails
import com.example.bink.igottamealing.model.MealDetailsList
import kotlinx.android.synthetic.main.view_meal.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MealDetailsViewModel @Inject constructor(
    private val resources: Resources,
    private val cachedMealsService: CachedMealsService
) :
    ViewModel() {

    lateinit var mealId: String

    private val _image = MutableLiveData<String>()
    val image: LiveData<String> = _image

    private val _imageDescription = MutableLiveData<String>("A placeholder image")
    val imageDescription: LiveData<String> = _imageDescription

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _detailsLoaded = MutableLiveData<Boolean>()
    val detailsLoaded: LiveData<Boolean> = _detailsLoaded

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    val ingredients = mutableListOf<Ingredient>()

    val instructions = mutableListOf<String>()

    fun onViewCreated() {
        cachedMealsService.getMealDetails(mealId, {
            it?.let { updateMealDetails(it) }
        }, {
            showError(it)
        })
    }

    private fun updateMealDetails(mealDetails: MealDetails) {
        _image.postValue(mealDetails.strMealThumb)
        _imageDescription.postValue(
            resources.getString(
                R.string.meal_image_description,
                mealDetails.strMeal
            )
        )
        _title.postValue(mealDetails.strMeal)
        ingredients.addAll(mealDetails.getIngredients())
        mealDetails.strInstructions?.let { instructions.addAll(it.split("\n").filter { str -> str.replace(Regex("\\s+"), "").isNotEmpty() }) }
        _detailsLoaded.postValue(true)
    }

    private fun showError(text: String?) {
        _errorText.postValue(resources.getString(R.string.error_message, text))
        _detailsLoaded.postValue(false)
    }
}