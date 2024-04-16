package com.example.weatherappnew.domain.repository

import com.example.weatherappnew.domain.util.Resource
import com.example.weatherappnew.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}