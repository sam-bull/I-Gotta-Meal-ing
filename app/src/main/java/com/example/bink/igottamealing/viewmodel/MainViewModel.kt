package com.example.bink.igottamealing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Categories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(val mealsService: MealsService) : ViewModel() {

    companion object {
        // For real API keys, we should have a more secure storage solution
        private const val API_KEY = "1"
    }

    val categories = mutableListOf<String>()

    private val _categoriesLoaded = MutableLiveData<Boolean>()
    val categoriesLoaded: LiveData<Boolean> = _categoriesLoaded

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun onViewCreated() {
        mealsService.getCategories(API_KEY).enqueue(object : Callback<Categories> {
            override fun onResponse(
                call: Call<Categories>,
                response: Response<Categories>
            ) {
                println(response.body().toString())
                response.body()
                    ?.categories?.let { categories.addAll(it.map { category -> category.strCategory }) }
                _categoriesLoaded.postValue(true)
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                _categoriesLoaded.postValue(false)
                _errorText.postValue(t.message)
            }
        })
    }

}
