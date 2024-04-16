package com.example.weatherappnew.data.mappers

import android.util.Log
import com.example.weatherappnew.data.remote.WeatherDataDto
import com.example.weatherappnew.data.remote.WeatherDto
import com.example.weatherappnew.domain.weather.WeatherData
import com.example.weatherappnew.domain.weather.WeatherDayInfo
import com.example.weatherappnew.domain.weather.WeatherInfo
import com.example.weatherappnew.domain.weather.WeatherType
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature.toInt(),
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentHour = if (now.minute < 30) now.hour else now.hour + 1
    val currentDayIndex = 0

    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    val filteredWeatherDataList = mutableListOf<List<WeatherData>>()

    val currentDayData = weatherDataMap[currentDayIndex]?.filter { it.time.hour >= currentHour }
    if (currentDayData != null) {
        filteredWeatherDataList.add(currentDayData)
    }

    val nextDayIndex = currentDayIndex + 1
    val nextDayData = weatherDataMap[nextDayIndex]
    if (nextDayData != null) {
        val remainingHours = 24 - (currentDayData?.size ?: 0)
        val nextDayHours = nextDayData.take(remainingHours)
        filteredWeatherDataList.add(nextDayHours)
    }

    val weatherDataWeekly = weatherDataMap.map { it.value.computeWeatherDayInfo() }

    return WeatherInfo(
        weatherDataPerDay = filteredWeatherDataList,
        currentWeatherData = currentWeatherData,
        weeklyWeatherData = weatherDataWeekly
    )
}

fun List<WeatherData>.computeWeatherDayInfo(): WeatherDayInfo {
    val dayInfo = this.firstOrNull()?.time?.dayOfWeek?.getDisplayName(TextStyle.FULL,
        Locale.getDefault()) ?: "Monday"
    val maxTemperature = this.maxOfOrNull { it.temperatureCelsius } ?: 0.0
    val minTemperature = this.minOfOrNull { it.temperatureCelsius } ?: 0.0
    val avgHumidity = "${(this.map { it.humidity }.average()).toInt()}%"
    val dayWeather: WeatherType = this.firstOrNull { it.time.hour == 12 }?.weatherType
        ?: WeatherType.PartlyCloudy
    val nightWeather: WeatherType = this.firstOrNull { it.time.hour == 0 || it.time.hour == 23 }
        ?.weatherType ?: WeatherType.PartlyCloudy

    return WeatherDayInfo(
        day = dayInfo,
        maxTemperature = maxTemperature.toInt(),
        minTemperature = minTemperature.toInt(),
        avgHumidity = avgHumidity,
        dayWeather = dayWeather,
        nightWeather = nightWeather
    )
}
