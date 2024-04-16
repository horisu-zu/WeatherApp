package com.example.weatherappnew.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    val combinedData = state.weatherInfo?.weatherDataPerDay
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "For 24 hours",
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = modifier
                .clip(RoundedCornerShape(15.dp))
                .border(width = 1.dp, color = Color.LightGray)
        ) {
            LazyRow(content = {
                combinedData?.forEach { hourList ->
                    items(hourList) { weatherData ->
                        HourlyWeatherDisplay(
                            weatherData = weatherData,
                            modifier = Modifier
                                .padding(horizontal = 18.dp, vertical = 9.dp)
                        )
                    }
                }
            })
        }
    }
}