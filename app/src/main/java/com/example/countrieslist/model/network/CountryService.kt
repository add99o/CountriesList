package com.example.countrieslist.model.network

import com.example.countrieslist.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {
    @GET(NetworkConstant.ENDPOINT)
    suspend fun getAllCountries(): Response<List<Country>>
}
