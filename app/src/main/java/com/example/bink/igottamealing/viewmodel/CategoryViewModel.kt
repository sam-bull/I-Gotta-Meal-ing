package com.example.bink.igottamealing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Meal
import com.example.bink.igottamealing.model.Meals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryViewModel @Inject constructor(private val mealsService: MealsService) : ViewModel() {

    lateinit var category: String
    val meals = mutableListOf<Meal>()

    private val _mealsLoaded = MutableLiveData<Boolean>()
    val mealsLoaded: LiveData<Boolean> = _mealsLoaded

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun onViewCreated() {
        mealsService.getMealsForCategory(MealsService.API_KEY, category)
            .enqueue(object : Callback<Meals> {
                override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                    if (response.isSuccessful) {
                        response.body()?.meals?.let {
                            meals.addAll(it)
                        }
                        _mealsLoaded.postValue(true)
                    } else {
                        _mealsLoaded.postValue(false)
                        _errorText.postValue(response.errorBody()?.string())
                    }
                }

                override fun onFailure(call: Call<Meals>, t: Throwable) {
                    _mealsLoaded.postValue(false)
                    _errorText.postValue(t.message)
                }
            })
    }
}