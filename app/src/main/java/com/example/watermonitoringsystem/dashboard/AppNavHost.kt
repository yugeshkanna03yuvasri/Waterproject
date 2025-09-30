package com.example.watermonitoringsystem.dashboard

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.watermonitoringsystem.ViewModel.SymptomViewModel
import com.example.watermonitoringsystem.ui.HomeScreen

@Composable
fun AppNavHost(viewModel: SymptomViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") {
            HomeScreen(navController = navController)
        }
        composable("selectLocation") { LocationSelectionScreen(navController) }
        composable("reportSymptoms") {
            ReportSymptomsScreen(viewModel = viewModel, navController = navController)
        }
        composable("viewIoTData") {
            ViewIoTDataScreen(navController)
        }
        composable("waterReport") {
            WaterQualityReportScreen(navController)
        }
        composable("education") {
            EducationHubScreen(navController)
        }
        composable("profile") {
            ProfileCreationScreen() // our profile creation screen
        }
    }
}
