package com.afif.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afif.weatherapp.model.CurrentWeatherResponse
import com.afif.weatherapp.model.ForecastWeatherResponse
import com.afif.weatherapp.network.ApiClient.apiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val currentWeather = MutableLiveData<CurrentWeatherResponse>()
    private val forecastWeather = MutableLiveData<ForecastWeatherResponse>()

    val getCurrentWeatherByCoordinate: LiveData<CurrentWeatherResponse> = currentWeather
    val getForecastWeatherByCoordinate: LiveData<ForecastWeatherResponse> = forecastWeather

    fun currentWeatherByCoordinate(lat: Double, lon: Double) {

        apiClient().currentWeatherByCoordinate(lat, lon)
            .enqueue(object : Callback<CurrentWeatherResponse> {
                override fun onResponse(
                    call: Call<CurrentWeatherResponse>,
                    response: Response<CurrentWeatherResponse>
                ) {
//                    currentWeather.value = response.body()
                    currentWeather.postValue(response.body())
                }

                override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                    Log.e("CURRENT_WEATHER", "onFailure: ${t.message}")
                }

            })
    }

    fun forecastWeatherByCoordinate(lat: Double, lon: Double){
        apiClient().forecastWeatherByCoordinate(lat,lon).enqueue(object : Callback<ForecastWeatherResponse> {
            override fun onResponse(
                call: Call<ForecastWeatherResponse>,
                response: Response<ForecastWeatherResponse>
            ) {
               forecastWeather.postValue(response.body())
            }

            override fun onFailure(call: Call<ForecastWeatherResponse>, t: Throwable) {
                Log.e("FORECAST_WEATHER", "onFailure: ${t.message}", )
            }

        })
    }

}