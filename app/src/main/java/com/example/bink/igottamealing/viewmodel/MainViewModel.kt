package com.example.bink.igottamealing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val categories = mutableListOf<String>()

    private val _categoriesLoaded = MutableLiveData<Boolean>()
    val categoriesLoaded: LiveData<Boolean> = _categoriesLoaded

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun onViewCreated() {
        // TODO replace with API call
        categories.addAll(listOf("pasta", "rice", "pastry", "potato"))

        _categoriesLoaded.postValue(true)
    }

}
