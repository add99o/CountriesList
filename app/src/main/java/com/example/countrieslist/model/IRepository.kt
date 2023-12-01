package com.example.countrieslist.model

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getAllCountries(): Flow<UIState>
}
