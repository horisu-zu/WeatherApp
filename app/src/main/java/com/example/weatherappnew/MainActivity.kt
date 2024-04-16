package com.example.weatherappnew

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherappnew.presentation.WeatherCard
import com.example.weatherappnew.presentation.WeatherForecast
import com.example.weatherappnew.presentation.WeatherViewModel
import com.example.weatherappnew.presentation.WeeklyForecast
//import com.example.weatherappnew.presentation.WeeklyForecast
import com.example.weatherappnew.ui.theme.DarkBlue
import com.example.weatherappnew.ui.theme.DeepBlue
import com.example.weatherappnew.ui.theme.PurpleGrey80
import com.example.weatherappnew.ui.theme.WeatherAppNewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        setContent {
            WeatherAppNewTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.DarkGray)
                    ) {
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = DeepBlue,
                            borderColor = PurpleGrey80
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = viewModel.state)
                        Spacer(modifier = Modifier.height(16.dp))
                        WeeklyForecast(state = viewModel.state)
                    }
                    if(viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}