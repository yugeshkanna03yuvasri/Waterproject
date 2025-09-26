package com.example.watermonitoringsystem.dashboard

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitoringsystem.R

/**
 * HeroBanner shows an image (or Lottie) with a centered title & quote.
 * - For best results, add a banner image to res/drawable/banner_health.jpg
 * - Alternatively, replace the Image composable with a LottieAnimation if you add lottie assets.
 */
@Composable
fun HeroBanner(modifier: Modifier = Modifier) {
    // subtle "breathing" scale animation to make banner feel alive
    var animate by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (animate) 1f else 0.98f,
        animationSpec = tween(durationMillis = 1600, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        animate = true
    }

    Card(
        modifier = modifier.scale(scale),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image (add banner to drawable)
            Image(
                painter = painterResource(id = R.drawable.healthcare_banner),
                contentDescription = "Health Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // a gradient overlay to ensure text legibility
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0x00000000), Color(0xAA0B3B2E)),
                            startY = 30f
                        )
                    )
            )

            // Centered Title + Quote
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Early Prediction System",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.White, fontSize = 22.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "“Safe Water, Healthy Lives”",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFFDDECE3))
                )
            }
        }
    }
}
