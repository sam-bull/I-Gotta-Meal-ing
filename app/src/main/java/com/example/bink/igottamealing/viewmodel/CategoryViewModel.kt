package com.example.bink.igottamealing.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.api.CachedMealsService
import com.example.bink.igottamealing.model.Meal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryViewModel @Inject constructor(
    private val resources: Resources,
    private val mealsService: CachedMealsService
) : ViewModel() {

    lateinit var category: String
    val meals = mutableListOf<Meal>()

    private val _mealsLoaded = MutableLiveData<Boolean>()
    val mealsLoaded: LiveData<Boolean> = _mealsLoaded

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun onViewCreated() {
        getMeals()
    }

    fun onRefresh() {
        getMeals()
    }

    private fun getMeals() {
        mealsService.getMealsForCategory(category, {
            it?.let {
                meals.clear()
                meals.addAll(it)
            }
            _mealsLoaded.postValue(true)
        }, { showError(it) })
    }

    private fun showError(text: String?) {
        _mealsLoaded.postValue(false)
        _errorText.postValue(resources.getString(R.string.error_message, text))
    }
}