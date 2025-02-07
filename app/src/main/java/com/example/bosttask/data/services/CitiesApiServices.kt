package com.example.bosttask.data.services

import com.example.bosttask.data.model.CitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApiServices {
    @GET("cities/getAllDistricts")
    suspend fun getAllDistricts(@Query("countryId") countryId: String): Response<CitiesResponse?>
}