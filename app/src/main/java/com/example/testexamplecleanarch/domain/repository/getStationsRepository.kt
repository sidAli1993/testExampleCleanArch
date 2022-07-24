package com.example.testexamplecleanarch.domain.repository

import com.example.testexamplecleanarch.common.Response
import com.example.testexamplecleanarch.data.model.BikeStationsModel
import kotlinx.coroutines.flow.Flow
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call

interface getStationsRepository {
    fun getstations(mtype:String, co:String): Flow<Response<BikeStationsModel>>
}