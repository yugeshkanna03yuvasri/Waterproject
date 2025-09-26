package com.example.watermonitoringsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.watermonitoringsystem.dashboard.ContactScreenCards
import com.example.watermonitoringsystem.dashboard.CurrentCasesSection
import com.example.watermonitoringsystem.dashboard.FeatureSection
import com.example.watermonitoringsystem.dashboard.HealthBannerContent
import com.example.watermonitoringsystem.dashboard.MissionsSection
import com.example.watermonitoringsystem.dashboard.TrustedBySection

@Composable
fun HomeScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = Color(0xFFF6F7FB))
        ) {
            // Top Header
            HealthBannerContent(navController = navController)

            Spacer(modifier = Modifier.height(12.dp))

            // Feature Cards Grid
            FeatureSection(navController = navController)

            Spacer(modifier = Modifier.height(24.dp))

            MissionsSection()


            Spacer(modifier = Modifier.height(24.dp))

//            ContactScreen()
            ContactScreenCards()

        }
    }
}
