package com.gl4.tp5mobile

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gl4.tp5mobile.forecast_models.ForecastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel(private val context : Context) {
    //utilisée pour émettre des mises à jour de données
    private val forecastResponse = MutableLiveData<ForecastResponse>()
    //ne peut pas être modifiée directement de l'extérieur de la classe
    var forecast : LiveData<ForecastResponse> = forecastResponse


    // obtenir les prévisions météorologiques pour la ville spécifiée
    // l'appel réseau s'effectue de manière asynchrone avec enqueue
    fun getForecast(city : String){
        RetrofitHelper.retrofitService.getForecast(city).enqueue(
            object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if(response.isSuccessful){
                        forecastResponse.value = response.body()
                        forecast = forecastResponse
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                }
            }
        )
    }

}