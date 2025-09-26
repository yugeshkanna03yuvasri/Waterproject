package com.example.watermonitoringsystem.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewIoTDataScreen(navController: NavController) {
    val regions = listOf(
        "Assam" to listOf("Brahmaputra", "Barak River", "Deepor Beel"),
        "Meghalaya" to listOf("Umiam Lake", "Ward’s Lake"),
        "Manipur" to listOf("Loktak Lake"),
        "Mizoram" to listOf("Tlawng River", "Khawthlangtuipui"),
        "Nagaland" to listOf("Doyang River", "Diphupar Stream"),
        "Tripura" to listOf("Gumti River", "Howrah River"),
        "Arunachal Pradesh" to listOf("Siang River", "Subansiri River"),
        "Sikkim" to listOf("Teesta River", "Rangit River")
    )

    var expandedRegion by remember { mutableStateOf<String?>(null) }
    var selectedWaterBody by remember { mutableStateOf<String?>(null) }

    val parameters = listOf(
        Triple("pH", "7.5", Icons.Default.Science),
        Triple("Turbidity", "3.2 NTU", Icons.Default.InvertColors),
        Triple("Dissolved Oxygen", "9.2 mg/L", Icons.Default.WaterDrop),
        Triple("E. coli Count", "1 CFU/100mL", Icons.Default.BugReport),
        Triple("Temperature", "19.8 °C", Icons.Default.Thermostat)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "IoT Data Dashboard",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF0D47A1)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF0D47A1)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                "Select a Region:",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0D47A1),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Expandable Region List
            LazyColumn(
                modifier = Modifier.weight(1f, fill = true)
            ) {
                items(regions.size) { index ->
                    val region = regions[index]

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(Color(0xFFF1F5FB), shape = MaterialTheme.shapes.medium)
                            .clickable {
                                expandedRegion =
                                    if (expandedRegion == region.first) null else region.first
                                selectedWaterBody = null
                            }
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = region.first,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                            Icon(
                                imageVector = if (expandedRegion == region.first) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = "Expand",
                                tint = Color(0xFF0D47A1)
                            )
                        }

                        // Water Bodies Expandable List
                        AnimatedVisibility(
                            visible = expandedRegion == region.first,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            Column(modifier = Modifier.padding(top = 8.dp)) {
                                region.second.forEach { water ->
                                    Text(
                                        text = water,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                selectedWaterBody = water
                                            }
                                            .padding(12.dp),
                                        fontSize = 15.sp,
                                        color = if (selectedWaterBody == water) Color(0xFF1976D2) else Color.DarkGray,
                                        fontWeight = if (selectedWaterBody == water) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // IoT Data Display
            if (selectedWaterBody != null) {
                Text(
                    selectedWaterBody!!,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF0D47A1),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f, fill = true)
                ) {
                    items(parameters.size) { index ->
                        ParameterRow(
                            icon = parameters[index].third,
                            title = parameters[index].first,
                            value = parameters[index].second
                        )
                        Divider(color = Color(0xFFE0E0E0))
                    }
                }
            } else {
                Text(
                    "Please select a water body to view IoT data.",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun ParameterRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                title,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
        Text(
            value,
            fontSize = 15.sp,
            color = Color(0xFF0D47A1),
            fontWeight = FontWeight.Bold
        )
    }
}
