package com.example.weatherappnew.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun WeeklyForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    val weeklyData = state.weatherInfo?.weeklyWeatherData
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Weekly Forecast",
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = modifier
                .clip(RoundedCornerShape(15.dp))
                .border(width = 1.dp, color = Color.LightGray)
                .fillMaxWidth()
        ) {
            LazyColumn(content = {
                weeklyData?.forEach { dayInfo ->
                    item {
                        DailyWeatherDisplay(
                            weatherData = dayInfo,
                            modifier = Modifier
                                .padding(horizontal = 18.dp, vertical = 9.dp)
                        )
                    }
                }
            })
        }
    }
}
