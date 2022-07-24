package com.example.testexamplecleanarch.domain.usecase

import com.example.testexamplecleanarch.common.Response
import com.example.testexamplecleanarch.data.model.BikeStationsModel
import com.example.testexamplecleanarch.domain.repository.getStationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

class getStationsUseCase @Inject constructor(private val getStationsRepository: getStationsRepository) {

    operator fun invoke(mtype: String, co: String): Flow<Response<BikeStationsModel>>{
        return getStationsRepository.getstations(mtype, co)
    }
}