package com.example.countrieslist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.countrieslist.model.IRepository
import com.example.countrieslist.model.RepositoryImpl
import com.example.countrieslist.model.UIState
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

    private fun loadServiceCountry() {
        viewModelScope.launch {
            try {
                repository.getAllCountries().collect {
                    _countries.value = it
                }
            } catch (e: Exception) {
                _countries.value = UIState.Failure(e.message ?: "Unknown error")
            }
        }
    }
}
