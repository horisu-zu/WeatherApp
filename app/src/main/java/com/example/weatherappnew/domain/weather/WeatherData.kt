package com.example.weatherappnew.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Int,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)

data class WeatherDayInfo(
    val day: String,
    val maxTemperature: Int,
    val minTemperature: Int,
    val avgHumidity: String,
    val dayWeather: WeatherType,
    val nightWeather: WeatherType
)