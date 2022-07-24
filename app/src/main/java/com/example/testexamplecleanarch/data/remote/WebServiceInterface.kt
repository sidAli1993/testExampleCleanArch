package com.example.testexamplecleanarch.data.remote

import com.example.testexamplecleanarch.data.model.BikeStationsModel
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServiceInterface {

    @GET("map_service.html")
    suspend fun getStations(@Query("mtype") mtype: String, @Query("co") co: String): BikeStationsModel
}