package com.example.watermonitoringsystem.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// ✅ Data model
data class WaterQualityReport(
    val location: String,
    val date: String,
    val volunteer: String,
    val parameters: Map<String, String>,
    val conclusion: String,
    val status: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterQualityReportScreen(navController: NavController) {
    // Regions and water bodies
    val regionReports = mapOf(
        "Assam" to listOf(
            WaterQualityReport("Brahmaputra River", "20 Sept 2025", "Rahul Sharma",
                mapOf("pH" to "7.2", "Turbidity" to "2.8 NTU", "DO" to "8.5 mg/L"),
                "Safe for consumption.", "Good"),
            WaterQualityReport("Barak River", "19 Sept 2025", "Priya Das",
                mapOf("pH" to "6.8", "Turbidity" to "5.1 NTU", "DO" to "6.9 mg/L"),
                "Moderate contamination detected.", "Warning"),
            WaterQualityReport("Deepor Beel", "18 Sept 2025", "Rohit Sen",
                mapOf("pH" to "6.4", "Turbidity" to "7.2 NTU", "DO" to "5.2 mg/L"),
                "Unsuitable without treatment.", "Critical")
        ),
        "Sikkim" to listOf(
            WaterQualityReport("Teesta River", "20 Sept 2025", "Anita Gurung",
                mapOf("pH" to "6.4", "Turbidity" to "4.5 NTU", "DO" to "6.8 mg/L"),
                "Requires treatment.", "Warning"),
            WaterQualityReport("Rangit River", "19 Sept 2025", "Dawa Lepcha",
                mapOf("pH" to "7.1", "Turbidity" to "3.0 NTU", "DO" to "8.2 mg/L"),
                "Safe for general use.", "Good")
        ),
        "Meghalaya" to listOf(
            WaterQualityReport("Umiam Lake", "21 Sept 2025", "Sandeep Roy",
                mapOf("pH" to "6.9", "Turbidity" to "2.9 NTU", "DO" to "7.4 mg/L"),
                "Water is clean.", "Good"),
            WaterQualityReport("Ward’s Lake", "20 Sept 2025", "Mary Khongwir",
                mapOf("pH" to "6.5", "Turbidity" to "6.0 NTU", "DO" to "5.8 mg/L"),
                "Requires treatment before use.", "Warning")
        ),
        "Manipur" to listOf(
            WaterQualityReport("Loktak Lake", "20 Sept 2025", "Thangjam Ibobi",
                mapOf("pH" to "7.0", "Turbidity" to "3.2 NTU", "DO" to "7.8 mg/L"),
                "Healthy aquatic life.", "Good"),
            WaterQualityReport("Nambul River", "19 Sept 2025", "Meena Devi",
                mapOf("pH" to "6.3", "Turbidity" to "6.4 NTU", "DO" to "5.5 mg/L"),
                "Pollution observed.", "Warning")
        ),
        "Mizoram" to listOf(
            WaterQualityReport("Tlawng River", "18 Sept 2025", "Lalrintluanga",
                mapOf("pH" to "6.9", "Turbidity" to "3.6 NTU", "DO" to "7.1 mg/L"),
                "Safe for daily use.", "Good"),
            WaterQualityReport("Khawthlangtuipui River", "17 Sept 2025", "Rinchen Pachuau",
                mapOf("pH" to "6.5", "Turbidity" to "7.0 NTU", "DO" to "5.6 mg/L"),
                "High turbidity detected.", "Warning")
        ),
        "Nagaland" to listOf(
            WaterQualityReport("Doyang River", "21 Sept 2025", "Ato Yepthomi",
                mapOf("pH" to "7.3", "Turbidity" to "3.0 NTU", "DO" to "8.0 mg/L"),
                "Water is clean.", "Good"),
            WaterQualityReport("Chathe River", "20 Sept 2025", "Zhopra Vero",
                mapOf("pH" to "6.7", "Turbidity" to "5.5 NTU", "DO" to "6.0 mg/L"),
                "Requires monitoring.", "Warning")
        ),
        "Tripura" to listOf(
            WaterQualityReport("Gumti River", "19 Sept 2025", "Ratan Debbarma",
                mapOf("pH" to "6.9", "Turbidity" to "4.2 NTU", "DO" to "7.2 mg/L"),
                "Good quality water.", "Good"),
            WaterQualityReport("Howrah River", "18 Sept 2025", "Subhra Paul",
                mapOf("pH" to "6.4", "Turbidity" to "6.8 NTU", "DO" to "5.9 mg/L"),
                "Contamination present.", "Warning")
        ),
        "Arunachal Pradesh" to listOf(
            WaterQualityReport("Siang River", "20 Sept 2025", "Tsering Wangchu",
                mapOf("pH" to "7.4", "Turbidity" to "2.5 NTU", "DO" to "8.6 mg/L"),
                "Pristine quality water.", "Good"),
            WaterQualityReport("Subansiri River", "19 Sept 2025", "Anong Apang",
                mapOf("pH" to "6.6", "Turbidity" to "6.2 NTU", "DO" to "5.7 mg/L"),
                "Moderate pollution detected.", "Warning")
        )
    )

    var selectedRegion by remember { mutableStateOf<String?>(null) }
    var selectedReport by remember { mutableStateOf<WaterQualityReport?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Water Quality Reports",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF0D47A1)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF0D47A1))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Animate outline color
            val borderColor by animateColorAsState(
                if (expanded) Color(0xFF1976D2) else Color.Gray,
                label = "borderAnim"
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedRegion ?: "Select Region",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = { Text("Region") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        focusedLabelColor = Color(0xFF1976D2),
                        unfocusedLabelColor = Color.DarkGray,
                        cursorColor = Color(0xFF1976D2),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    )
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(Color.White) // white background
                        .width(220.dp)           // control width
                        .heightIn(max = 250.dp)  // control height
                ) {
                    regionReports.keys.forEach { region ->
                        DropdownMenuItem(
                            text = { Text(region, color = Color.Black) }, // ensure black text
                            onClick = {
                                selectedRegion = region
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show water bodies
            selectedRegion?.let { region ->
                val reports = regionReports[region] ?: emptyList()
                LazyColumn {
                    items(reports.size) { idx ->
                        val report = reports[idx]
                        ReportCard(report = report, onClick = { selectedReport = report })
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            // Details dialog
            AnimatedVisibility(
                visible = selectedReport != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                selectedReport?.let { report ->
                    ReportDetailDialog(report = report, onDismiss = { selectedReport = null })
                }
            }
        }
    }
}

@Composable
fun ReportCard(report: WaterQualityReport, onClick: () -> Unit) {
    val statusColor = when (report.status) {
        "Good" -> Color(0xFF4CAF50)
        "Warning" -> Color(0xFFFF9800)
        "Critical" -> Color(0xFFF44336)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(report.location, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF0D47A1))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Date: ${report.date}", fontSize = 14.sp, color = Color.DarkGray)
            Text("Volunteer: ${report.volunteer}", fontSize = 14.sp, color = Color.DarkGray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(report.conclusion, fontSize = 15.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                report.status,
                color = statusColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier
                    .background(statusColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun ReportDetailDialog(report: WaterQualityReport, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close", color = Color(0xFF0D47A1)) }
        },
        title = {
            Text(report.location, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF0D47A1))
        },
        text = {
            Column {
                Text("Date: ${report.date}", fontSize = 14.sp, color = Color.Gray)
                Text("Volunteer: ${report.volunteer}", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(12.dp))
                report.parameters.forEach { (param, value) ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(param, fontWeight = FontWeight.Medium, fontSize = 15.sp)
                        Text(value, color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(report.conclusion, fontSize = 15.sp, color = Color.Black)
            }
        },
        containerColor = Color.White,
        tonalElevation = 6.dp,
        shape = RoundedCornerShape(16.dp)
    )
}
