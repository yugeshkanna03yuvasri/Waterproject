package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PointsSection(points: Int) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = "Your Points: $points", fontSize = 16.sp, color = Color(0xFF2E7D32))
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = (points % 200) / 200f,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF2E7D32),
            trackColor = Color(0xFFC8E6C9)
        )
    }
}
