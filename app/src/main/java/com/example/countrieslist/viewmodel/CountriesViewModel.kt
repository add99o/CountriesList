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
            _countries.value = UIState.Loading(true) // Set loading state to true at the beginning

            try {
                repository.getAllCountries().collect {
                    _countries.value = UIState.Loading(false) // Set loading state to false when data starts arriving
                    _countries.value = it // Update with data (Success or Failure)
                }
            } catch (e: Exception) {
                _countries.value = UIState.Loading(false) // Ensure loading is set to false on exception
                _countries.value = UIState.Failure(e.message ?: "Unknown error")
            }
        }
    }
}
