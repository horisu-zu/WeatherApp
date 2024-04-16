package com.example.weatherappnew.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappnew.R
import com.example.weatherappnew.domain.weather.WeatherDayInfo

@Composable
fun DailyWeatherDisplay(
    weatherData: WeatherDayInfo,
    textColor: Color = Color.White,
    modifier: Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = weatherData.day,
            modifier = Modifier
                .weight(3f),
            fontSize = 14.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(2f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = weatherData.avgHumidity,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_drop),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        Image(
            painter = painterResource(id = weatherData.dayWeather.iconRes),
            contentDescription = null,
            modifier = Modifier
                .width(25.dp)
        )
        Spacer(modifier = Modifier.weight(0.2f))
        Image(
            painter = painterResource(id = weatherData.nightWeather.iconRes),
            contentDescription = null,
            modifier = Modifier
                .width(25.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "${weatherData.maxTemperature}°/ ${weatherData.minTemperature}°",
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(2f)
        )
    }
}