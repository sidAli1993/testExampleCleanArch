package com.example.testexamplecleanarch.data.repository

import android.util.Log
import com.example.testexamplecleanarch.common.Response
import com.example.testexamplecleanarch.data.model.BikeStationsModel
import com.example.testexamplecleanarch.data.remote.WebServiceInterface
import com.example.testexamplecleanarch.domain.repository.getStationsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

class BikeStationsRepositoryImpl @Inject constructor(private val webServiceInterface: WebServiceInterface) :
    getStationsRepository {
    override fun getstations(mtype: String, co: String): Flow<Response<BikeStationsModel>> =
        channelFlow {
            try {
                trySend(Response.Loading)
                val resp=webServiceInterface.getStations(mtype, co)
                Log.e("TAG", "response in impl $resp", )
                trySend(Response.Success(resp))


            } catch (e: Exception) {
                trySend(Response.Error(e.localizedMessage ?: "unexpected error"))
            }
            awaitClose()
        }


}