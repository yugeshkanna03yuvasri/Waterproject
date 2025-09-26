package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


// 🔹 Horizontally scrollable Feature Section with square cards
@Composable
fun FeatureSection(navController: NavController) {
    val features = listOf(
        Triple("Report Symptoms", "Quickly log early signs.Help track community health.Enable proactive response", Icons.Default.Mic),
        Triple("Sensor Data", "Get real-time IoT readings.Check contamination levels.", Icons.Default.Sensors),
        Triple("Water Report", "See detailed reports.Track water quality status.Compare across regions", Icons.Default.WaterDrop),
        Triple("Learn & Prevent", "Understand safe practices.Get education resources.Stay protected from risks", Icons.Default.School)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
//        Text(
//            text = "Features",
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color(0xFF1565C0),
//            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
//        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            features.forEach { (title, desc, icon) ->
                FeatureCard(title, desc, icon, Modifier.padding(end = 12.dp)) {
                    when (title) {
                        "Report Symptoms" -> navController.navigate("reportSymptoms")
                        "Sensor Data" -> navController.navigate("viewIoTData")
                        "Water Report" -> navController.navigate("waterReport")
                        "Learn & Prevent" -> navController.navigate("education")
                    }
                }
            }
        }
    }
}




