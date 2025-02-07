package com.example.bosttask.data.repo

import com.example.bosttask.core.networking.ErrorResponse
import com.example.bosttask.core.networking.mapErrorToException
import com.example.bosttask.core.utilits.Resource
import com.example.bosttask.data.model.CitiesResponse
import com.example.bosttask.data.services.CitiesApiServices
import com.example.bosttask.domain.repo.CitiesRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val apiService: CitiesApiServices
) : CitiesRepository {
    override fun getAllCities(countryId: String): Flow<Resource<CitiesResponse?>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiService.getAllDistricts(countryId);
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            }

        } catch (error: Exception) {

            val exception = mapErrorToException(error)
            emit(
                Resource.Error(
                    exception = exception,
                )
            )
        }
    }
}