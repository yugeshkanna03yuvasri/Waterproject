package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EducationHubScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Water Health & Safety",
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
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            SectionTitle("1. Water Quality Explained (The What)")
            ParameterCard("pH", "Measures acidity, like how fruits can be sour or sweet.")
            ParameterCard("Dissolved Oxygen (DO)", "Fish need oxygen to breathe — DO shows how healthy water is.")
            ParameterCard("Nitrates & Phosphates", "Pollutants often from farms and waste. Too much harms water health.")

            Spacer(Modifier.height(20.dp))

            SectionTitle("2. The Dangers of Unsafe Water (The Why)")
            VideoCard("Diarrheal Diseases", "Short video on cholera, prevention like boiling water.","kuliQhjco9g")
            VideoCard("Typhoid", "Explains spread via contaminated water, importance of hygiene.","dae6VhLjT70")
            VideoCard("Fluorosis & Arsenicosis", "Shows long-term health risks from contaminated sources.","pARbkMVlofc")

            Spacer(Modifier.height(20.dp))

            SectionTitle("3. Using the Manual Test Kit (The How)")
            StepCard("Collect the sample", "Use a clean bottle and dip gently into the source.")
            StepCard("Add the reagents", "Follow kit instructions, drop chemicals carefully.")
            StepCard("Wait and observe", "Check for color change or reading after a few minutes.")
            StepCard("Record the results", "Use the app to log values and upload to dashboard.")

            Spacer(Modifier.height(20.dp))

            SectionTitle("4. Understanding Your Report (The So What)")
            LegendCard("Good", "The water is safe and healthy.", Color(0xFF4CAF50))
            LegendCard("Warning", "Some issues. Do not consume without treatment.", Color(0xFFFF9800))
            LegendCard("Critical", "Unsafe. Avoid use, report immediately.", Color(0xFFF44336))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0D47A1),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun ParameterCard(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
            Spacer(Modifier.height(4.dp))
            Text(description, color = Color.Black, fontSize = 14.sp)
        }
    }
}

@Composable
fun VideoCard(title: String, description: String, videoId: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            // 🎥 Placeholder thumbnail (replace with video player integration later)
//            Box(
//                modifier = Modifier
//                    .size(180.dp, 100.dp)
//                    .background(Color.LightGray, RoundedCornerShape(8.dp)),
//                contentAlignment = Alignment.Center
//            ) {
//                Text("Video Placeholder", color = Color.DarkGray, fontSize = 12.sp)
//            }
            YouTubeVideo(
                videoId = videoId,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
            Text(description, color = Color.Black, fontSize = 14.sp)
        }
    }
}

@Composable
fun StepCard(step: String, instruction: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFFE3F2FD), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFF0D47A1)),
            contentAlignment = Alignment.Center
        ) {
            Text(step.first().toString(), color = Color.White, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(step, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))
            Text(instruction, color = Color.Black, fontSize = 14.sp)
        }
    }
}

@Composable
fun LegendCard(status: String, explanation: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(status, fontWeight = FontWeight.Bold, color = color)
                Text(explanation, color = Color.Black, fontSize = 14.sp)
            }
        }
    }
}
