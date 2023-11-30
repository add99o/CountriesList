package com.example.countrieslist.model

import android.provider.Contacts.Intents.UI
import com.example.countrieslist.model.network.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl: IRepository {

    override fun getAllCountries(): Flow<UIState> {
        return flow {
            try {
                emit(UIState.Loading(true))
                val response = Network.service.getAllCountries()

                if (response.isSuccessful) {
                    val countries = response.body()
                    if (countries != null) {
                        emit(UIState.Success(countries))
                    } else {
                        emit(UIState.Failure("No data received"))
                    }
                } else {
                    emit(UIState.Failure("Error: ${response.message()}"))
                }
            } catch (e: Exception) {
                emit(UIState.Failure("Exception: ${e.localizedMessage}"))
            } finally {
                emit(UIState.Loading(false))
            }
        }
    }
}
