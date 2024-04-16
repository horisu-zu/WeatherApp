package com.example.weatherappnew.data.repository

import com.example.weatherappnew.data.mappers.toWeatherInfo
import com.example.weatherappnew.data.remote.WeatherApi
import com.example.weatherappnew.domain.repository.WeatherRepository
import com.example.weatherappnew.domain.util.Resource
import com.example.weatherappnew.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}