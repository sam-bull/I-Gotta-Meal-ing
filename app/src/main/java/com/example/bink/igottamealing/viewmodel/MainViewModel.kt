package com.example.bink.igottamealing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Categories
import com.example.bink.igottamealing.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val mealsService: MealsService) : ViewModel() {

    val categories = mutableListOf<Category>()

    private val _categoriesLoaded = MutableLiveData<Boolean>()
    val categoriesLoaded: LiveData<Boolean> = _categoriesLoaded

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun onViewCreated() {
        mealsService.getCategories(MealsService.API_KEY).enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful) {
                    response.body()?.categories?.let {
                        categories.addAll(it)
                    }
                    _categoriesLoaded.postValue(true)
                } else {
                    _categoriesLoaded.postValue(false)
                    _errorText.postValue(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                _categoriesLoaded.postValue(false)
                _errorText.postValue(t.message)
            }
        })
    }

}
