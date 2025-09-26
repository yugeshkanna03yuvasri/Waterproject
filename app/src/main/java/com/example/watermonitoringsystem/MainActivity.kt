package com.example.watermonitoringsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.watermonitoringsystem.ViewModel.SymptomViewModel
import com.example.watermonitoringsystem.dashboard.AppNavHost
import com.example.watermonitoringsystem.ui.theme.WaterMonitoringSystemTheme

class MainActivity : ComponentActivity() {

    // Create the ViewModel directly (no factory needed)
    private val symptomViewModel: SymptomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterMonitoringSystemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(viewModel = symptomViewModel)
                }
            }
        }
    }
}
