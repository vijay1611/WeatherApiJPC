package com.example.weatherapijpc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapijpc.api.NetworkResponse
import com.example.weatherapijpc.api.RetrofitInstance
import com.example.weatherapijpc.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel(){

    private  var weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
     val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult


        fun getData(q:String){

            _weatherResult.value = NetworkResponse.Loading

            viewModelScope.launch {
            try{
                val response =   weatherApi.getWeather(Constants.api_key, q)
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            }catch (e:Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }

            }

        }
}