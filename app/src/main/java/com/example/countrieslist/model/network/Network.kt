package com.example.countrieslist.model.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    val service: CountryService by lazy {
        createService()
    }

    private fun createService(): CountryService {
        return Retrofit.Builder()
            .baseUrl(NetworkConstant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CountryService::class.java)
    }
}