package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitoringsystem.R

@Composable
fun TopHeader(modifier: Modifier = Modifier, location: String) {
    // Big header with dark gradient background, rounded bottom corners
    Surface(
        modifier = modifier,
        shape = CutCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp),
        tonalElevation = 6.dp,
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF0F1724), Color(0xFF1E293B))
                    )
                )
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            // Top Row: location left, icons right
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "📍 $location",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    )
                }

                // RIGHT: Language & Profile icons
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { /* open language dialog */ },
                        modifier = Modifier.semantics { this.role = Role.Button }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.healthcare_banner),
                            contentDescription = "Change Language",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = { /* open profile */ },
                        modifier = Modifier.semantics { this.role = Role.Button }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.healthcare_banner),
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    }
                }
            }


        }
    }
}
