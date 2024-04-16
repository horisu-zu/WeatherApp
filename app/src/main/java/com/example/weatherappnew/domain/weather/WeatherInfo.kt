package com.example.weatherappnew.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: MutableList<List<WeatherData>>,
    val weeklyWeatherData: List<WeatherDayInfo>,
    val currentWeatherData: WeatherData?
)