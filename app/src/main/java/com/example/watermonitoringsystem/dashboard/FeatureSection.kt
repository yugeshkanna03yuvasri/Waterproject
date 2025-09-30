package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import com.example.watermonitoringsystem.R

// Helper data class for Feature information
data class FeatureInfo(
    val title: String,
    val icon: ImageVector,
    val accentColor: Color,
    val backgroundColor: Color,
    val route: String
)

@Composable
fun FeatureSection(navController: NavController) {
    val features = listOf(
        FeatureInfo(
            title = stringResource(R.string.report_a_case),
            icon = Icons.Default.Mic,
            accentColor = Color(0xFF1976D2),
            backgroundColor = Color(0xFFE3F2FD),
            route = "reportSymptoms"
        ),
        FeatureInfo(
            title = stringResource(R.string.sensor_data),
            icon = Icons.Default.Sensors,
            accentColor = Color(0xFF388E3C),
            backgroundColor = Color(0xFFE8F5E9),
            route = "viewIoTData"
        ),
        FeatureInfo(
            title = stringResource(R.string.water_report),
            icon = Icons.Default.WaterDrop,
            accentColor = Color(0xFF1565C0),
            backgroundColor = Color(0xFFB3E5FC),
            route = "waterReport"
        ),
        FeatureInfo(
            title = stringResource(R.string.learn_and_prevent),
            icon = Icons.Default.School,
            accentColor = Color(0xFF6A1B9A),
            backgroundColor = Color(0xFFF3E5F5),
            route = "education"
        )
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.app_utilities),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            features.forEach { info ->
                FeatureCard(
                    title = info.title,
                    icon = info.icon,
                    accentColor = info.accentColor,
                    backgroundColor = info.backgroundColor,
                    modifier = Modifier,
                    onClick = { navController.navigate(info.route) }
                )
            }
        }
    }
}


