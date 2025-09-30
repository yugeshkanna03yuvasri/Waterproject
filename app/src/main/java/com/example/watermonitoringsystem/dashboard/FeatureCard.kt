package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeatureCard(
    title: String,
    icon: ImageVector,
    accentColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(150.dp) // Fixed width for compact horizontal scroll
            .height(150.dp) // Fixed height (Square/Rectangular shape)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp), // ✅ Rounded Rectangular Shape
        colors = CardDefaults.cardColors(containerColor = backgroundColor), // 💡 Light background
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // Subtle elevation
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(45.dp), // Large Icon
                tint = accentColor // 💡 Colored Icon
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black, // Dark text color
                textAlign = TextAlign.Center,
                lineHeight = 16.sp,
                maxLines = 2
            )
        }
    }
}
