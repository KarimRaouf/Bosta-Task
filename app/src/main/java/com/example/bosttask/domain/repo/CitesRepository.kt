package com.example.bosttask.domain.repo



import com.example.bosttask.core.utilits.Resource
import com.example.bosttask.data.model.CitiesResponse
import kotlinx.coroutines.flow.Flow


interface CitiesRepository {
    fun getAllCities(countryId: String): Flow<Resource<CitiesResponse?>>
}