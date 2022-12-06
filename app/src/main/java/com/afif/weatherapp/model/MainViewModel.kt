package com.afif.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afif.weatherapp.model.CurrentWeatherResponse
import com.afif.weatherapp.network.ApiClient.apiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val currentWeather = MutableLiveData<CurrentWeatherResponse>()

    val getCurrentWeatherByCoordinate: LiveData<CurrentWeatherResponse> = currentWeather

    fun currentWeatherByCoordinate(lat: Double, long: Double) {

        apiClient().currentWeatherByCoordinate(lat, long)
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
}