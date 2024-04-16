package com.example.weatherappnew.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherappnew.R
import com.example.weatherappnew.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    textColor: Color = Color.White,
    modifier: Modifier
) {
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${weatherData.humidity.toInt()}%",
            color = textColor,
            modifier = Modifier
                .alpha(0.7f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_drop),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}