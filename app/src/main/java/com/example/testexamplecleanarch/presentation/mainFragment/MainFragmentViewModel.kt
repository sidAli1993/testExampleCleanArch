package com.example.testexamplecleanarch.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testexamplecleanarch.common.Response
import com.example.testexamplecleanarch.data.model.BikeStationsModel
import com.example.testexamplecleanarch.domain.usecase.getStationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONArray

import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val getStationsUseCase: getStationsUseCase) :
    ViewModel() {
    private val _resp = MutableStateFlow<Response<BikeStationsModel?>>(Response.Success(null))

    val resp: StateFlow<Response<BikeStationsModel?>> = _resp

    fun getStations(mtype: String, co: String) {
        viewModelScope.launch {
            getStationsUseCase.invoke(mtype, co).collect() {
                _resp.value = it
            }
        }
    }
}