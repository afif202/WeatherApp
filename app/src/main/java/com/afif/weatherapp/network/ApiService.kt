package com.afif.weatherapp.network

import com.afif.weatherapp.model.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun currentWeatherByCoordinate(
        @Query("lat") latitude: Double,
        @Query("lon") longtitude: Double,
        @Query("appid") apiKey: String = "f2a326bd173fa5c0317107286fa6515d"
    ) : Call<CurrentWeatherResponse>
}