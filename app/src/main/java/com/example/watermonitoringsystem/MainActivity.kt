package com.example.watermonitoringsystem

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import android.graphics.Color
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.watermonitoringsystem.ViewModel.SymptomViewModel
import com.example.watermonitoringsystem.dashboard.AppNavHost
import com.example.watermonitoringsystem.dashboard.LocaleHelper
import com.example.watermonitoringsystem.dashboard.LocaleHelper.getSavedLanguage
import com.example.watermonitoringsystem.ui.theme.WaterMonitoringSystemTheme

class MainActivity : ComponentActivity() {

    // Create the ViewModel directly (no factory needed)
    private val symptomViewModel: SymptomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        super.onCreate(savedInstanceState)
        setContent {
            WaterMonitoringSystemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .padding(WindowInsets.systemBars.asPaddingValues()),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(viewModel = symptomViewModel)
                }
            }
        }
    }
    override fun attachBaseContext(newBase: Context?) {
        val lang = getSavedLanguage(newBase) // load from SharedPreferences
        val context = if (lang != null) {
            LocaleHelper.wrapContext(newBase!!, lang)
        } else {
            newBase!!
        }
        super.attachBaseContext(context)
    }

}
