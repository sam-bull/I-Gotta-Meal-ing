package com.example.bink.igottamealing.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bink.igottamealing.R
import com.example.bink.igottamealing.api.CachedMealsService
import com.example.bink.igottamealing.api.MealsService
import com.example.bink.igottamealing.model.Categories
import com.example.bink.igottamealing.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(
    private val resources: Resources,
    private val cachedMealsService: CachedMealsService
) : ViewModel() {

    val categories = mutableListOf<Category>()

    private val _categoriesLoaded = MutableLiveData<Boolean>()
    val categoriesLoaded: LiveData<Boolean> = _categoriesLoaded

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun onViewCreated() {
        getCategories()
    }

    fun onRefresh() {
        getCategories()
    }

    private fun getCategories() {
        cachedMealsService.getCategories({
            it?.let {
                categories.clear()
                categories.addAll(it)
            }
            _categoriesLoaded.postValue(true)
        }, {
            showError(it)
        })
    }

    private fun showError(text: String?) {
        _categoriesLoaded.postValue(false)
        _errorText.postValue(resources.getString(R.string.error_message, text))
    }

}
