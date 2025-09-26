package com.example.watermonitoringsystem.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun HealthBannerContent(navController: NavController) {
    var selectedLocation by remember { mutableStateOf("Assam") }
    var expandedLocation by remember { mutableStateOf(false) }
    var expandedLanguage by remember { mutableStateOf(false) }

    val northeastStates = listOf(
        "Assam", "Manipur", "Meghalaya", "Mizoram",
        "Nagaland", "Tripura", "Arunachal Pradesh", "Sikkim"
    )
    val languages = listOf(
        "English", "Hindi", "Assamese", "Meitei",
        "Nagamese", "Tripuri", "Nepali", "Khasi"
    )

    // Animation for headline
    var animate by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (animate) 1f else 0.95f,
        animationSpec = tween(durationMillis = 1600, easing = FastOutSlowInEasing),
        label = "scaleAnim"
    )
    LaunchedEffect(Unit) { animate = true }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF1565C0),
                        Color(0xFF1565C0).copy(alpha = 0.75f),
                        Color(0xFF1565C0).copy(alpha = 0.5f),
                        Color.Transparent
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            // 🔝 Top bar: Location + Language + Profile
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 📍 Location dropdown
                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { expandedLocation = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = Color.Red
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = selectedLocation,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(" ▼", color = Color.White)
                    }

                    DropdownMenu(
                        expanded = expandedLocation,
                        onDismissRequest = { expandedLocation = false },
                        modifier = Modifier
                            .background(Color.White)
                            .width(180.dp)
                    ) {
                        northeastStates.forEach { state ->
                            DropdownMenuItem(
                                text = { Text(state, color = Color.Black) },
                                onClick = {
                                    selectedLocation = state
                                    expandedLocation = false
                                }
                            )
                        }
                    }
                }

                // 🌐 Language + 👤 Profile
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Language dropdown
                    IconButton(onClick = { navController.navigate("ai") }) {
                        Icon(
                            imageVector = Icons.Default.SmartToy,
                            contentDescription = "AI",
                            tint = Color.White
                        )
                    }
                    Box {

                        IconButton(onClick = { expandedLanguage = true }) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Language",
                                tint = Color.White
                            )
                        }
                        DropdownMenu(
                            expanded = expandedLanguage,
                            onDismissRequest = { expandedLanguage = false },
                            modifier = Modifier
                                .background(Color.White)
                                .width(160.dp)
                        ) {
                            languages.forEach { lang ->
                                DropdownMenuItem(
                                    text = { Text(lang, color = Color.Black) },
                                    onClick = {
                                        expandedLanguage = false
                                        // TODO: Handle language change
                                    }
                                )
                            }
                        }
                    }

                    // Profile icon
                    IconButton(onClick = { navController.navigate("profile")}) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "JeevanRaksha Jal",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(15.dp))

            // 🎯 Headline + Subheading + CTA
            AnimatedVisibility(visible = true) {


                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .scale(scale),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Proactive Health Monitoring for a Safer Future",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color(0xFFDDECE3),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    //Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Safe Water, Healthy lives",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFFDDECE3), fontSize = 16.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = { /* TODO: Navigate to Learn More */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .height(48.dp)
                            .width(160.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Text(
                            "Learn More",
                            color = Color(0xFF1565C0),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

