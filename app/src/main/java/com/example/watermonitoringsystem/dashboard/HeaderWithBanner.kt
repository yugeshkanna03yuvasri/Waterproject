package com.example.watermonitoringsystem.dashboard

import android.app.Activity
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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.watermonitoringsystem.R
import com.example.watermonitoringsystem.dashboard.LocaleHelper.saveLanguage
import com.example.watermonitoringsystem.datamodel.Village
// Assuming getSavedVillage is an extension function or in the same package/imported correctly

@Composable
fun getCurrentLangCode(): String {
    // Get the current configuration and locale from the Compose context
    val configuration = LocalConfiguration.current

    // Attempt to get the language tag from the first locale (primary language)
    // Returns "en" for English, "hi" for Hindi, etc.
    return configuration.locales.get(0).language
}

@Composable
fun HealthBannerContent(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity

    var savedVillage by remember { mutableStateOf<Village?>(null) }
    var expandedLanguage by remember { mutableStateOf(false) }

    // --- NEW: Get current language code for dynamic content translation ---
    val currentLangCode = getCurrentLangCode()
    // ---------------------------------------------------------------------

    LaunchedEffect(Unit) {
        savedVillage = getSavedVillage(context)
    }

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
            .height(360.dp)
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
                .padding(horizontal = 16.dp, vertical = 28.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        navController.navigate("selectLocation")
                    }
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = stringResource(R.string.select_your_village),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        if (savedVillage != null) {

                            // --- REQUIRED CHANGE: Retrieve the localized village name ---
                            val villageName = savedVillage!!.village[currentLangCode]
                                ?: savedVillage!!.village["en"]
                                ?: savedVillage!!.village.values.firstOrNull()
                                ?: ""

                            // --- REQUIRED CHANGE: Retrieve the localized district/state ---
                            val districtName = savedVillage!!.district[currentLangCode]
                                ?: savedVillage!!.district["en"]
                                ?: ""
                            val stateName = savedVillage!!.state[currentLangCode]
                                ?: savedVillage!!.state["en"]
                                ?: ""

                            Text(
                                // Use the localized village name
                                text = villageName,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                maxLines = 1
                            )
                            Text(
                                // Use the localized district and state names
                                text = "$districtName, $stateName",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                maxLines = 1
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.select_your_village),
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.navigate("ai") }) {
                        Icon(
                            Icons.Default.SmartToy,
                            contentDescription = stringResource(R.string.ai),
                            tint = Color.White
                        )
                    }
                    Box {
                        IconButton(onClick = { expandedLanguage = true }) {
                            Icon(
                                Icons.Default.Language,
                                contentDescription = stringResource(R.string.language),
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
                            listOf(
                                "English",
                                "Hindi",
                                "Assamese",
                                "Khasi",
                                "Manipuri",
                                "Meitei",
                                "Nepali"
                            ).forEach { lang ->
                                DropdownMenuItem(
                                    text = { Text(lang, color = Color.Black) },
                                    onClick = {
                                        expandedLanguage = false
                                        if (activity != null) {
                                            when (lang) {
                                                "Hindi" -> {
                                                    saveLanguage(activity, "hi")
                                                    activity.recreate()
                                                }
                                                "English" -> {
                                                    saveLanguage(activity, "en")
                                                    activity.recreate()
                                                }
                                                // Handle other languages similarly
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                    IconButton(onClick = { navController.navigate("profile") }) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = stringResource(R.string.profile),
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            AnimatedVisibility(visible = true) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.scale(scale),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.app_title),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = stringResource(R.string.tagline),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(0xFFDDECE3),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                        Text(
                            text = stringResource(R.string.subtitle),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFFDDECE3),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 24.dp)
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
                                stringResource(R.string.learn_more),
                                color = Color(0xFF1565C0),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}