package com.example.weatherapijpc.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    var base_url = "https://api.weatherapi.com"

    fun getRetrofit():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val weatherApi : WeatherApi = getRetrofit().create(WeatherApi::class.java)


}