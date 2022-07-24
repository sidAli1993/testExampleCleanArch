package com.example.testexamplecleanarch.hilt

import androidx.databinding.ktx.BuildConfig
import com.example.testexamplecleanarch.common.Constants
import com.example.testexamplecleanarch.data.remote.WebServiceInterface
import com.example.testexamplecleanarch.data.repository.BikeStationsRepositoryImpl
import com.example.testexamplecleanarch.domain.repository.getStationsRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun provideRetroservice(): WebServiceInterface {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(logging)
        }
        val gson = GsonBuilder()
            .serializeNulls()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
        httpClient.addInterceptor(logging)
        httpClient.readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create(WebServiceInterface::class.java)
    }
    @Provides
    fun providegetStationsRepo(webServiceInterface: WebServiceInterface):getStationsRepository{
        return BikeStationsRepositoryImpl(webServiceInterface)
    }
}