package com.example.countrieslist.model

sealed class UIState {
    data class Success(val listOfCountries: List<Country>): UIState()
    data class Failure(val errorMessage: String): UIState()
    data class Loading(val isLoading: Boolean = false): UIState()
}
