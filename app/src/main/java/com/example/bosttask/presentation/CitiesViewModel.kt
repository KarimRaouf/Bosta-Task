package com.example.bosttask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bosttask.core.utilits.Resource
import com.example.bosttask.data.model.CitiesResponse
import com.example.bosttask.domain.repo.CitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
) :ViewModel(){


    init {
        getCities("60e4482c7cb7d4bc4849c4d5")
    }
    private var _citiesstate = MutableSharedFlow<Resource<CitiesResponse?>>()
    val citiesState: SharedFlow<Resource<CitiesResponse?>> = _citiesstate.asSharedFlow()
    fun getCities(countyId:String) = viewModelScope.launch(Dispatchers.IO) {
        val response =  citiesRepository.getAllCities(countyId)
        response.collect{ state->
            when(state){
                is Resource.Loading -> {
                    _citiesstate.emit(Resource.Loading())
                }
                is Resource.Success -> {
                    _citiesstate.emit(Resource.Success(state.data))
                }

                is Resource.Idle -> _citiesstate.emit(Resource.Idle())
                is Resource.Success -> {
                    _citiesstate.emit(Resource.Success(state.data))
                }

                is Resource.Error -> {
                    _citiesstate.emit(
                        Resource.Error(
                            exception = state.exception,
                            errorResponse = state.errorResponse

                        ))
                }
            }

        }

    }
}