package com.example.countrieslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.model.Country
import com.example.countrieslist.model.IRepository
import com.example.countrieslist.model.RepositoryImpl
import com.example.countrieslist.model.UIState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountriesViewModel(application: Application) : AndroidViewModel(application) {
    private val _countries = MutableLiveData<UIState>()
    val countries: LiveData<UIState> = _countries
    private val repository: IRepository by lazy {
        RepositoryImpl()
    }
    init {
        loadServiceCountry()
    }

    private val countryExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.stackTrace
        _countries.value = UIState.Failure("The coroutine ${coroutineContext[CoroutineName]} fail: ${throwable.message}")
    }

    private fun loadServiceCountry() {
        viewModelScope.launch {
            repository.getAllCountries().collect {
                _countries.value = it
            }
        }
    }

    private fun loadCountries() {
        val jsonFileString = getJsonDataFromAsset(getApplication(), "countries.json")
        val gson = Gson()
        val listCountryType = object : TypeToken<List<Country>>() {}.type
        val countries: List<Country> = gson.fromJson(jsonFileString, listCountryType)
        //_countries.value = countries
    }

    private fun getJsonDataFromAsset(application: Application, fileName: String): String? {
        return application.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}