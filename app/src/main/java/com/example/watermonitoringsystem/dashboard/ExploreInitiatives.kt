package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitoringsystem.R
import androidx.compose.ui.res.stringResource


// 💡 1. Data Class for Mission - No change needed here
data class Mission(
    val title: String,
    //val subtitle: String,
    val imageResId: Int,
    val backgroundColor: Color // Background color for the circular card
)


@Composable
fun MissionsSection() {
    val dummyMissions = listOf(
        Mission(
            title = stringResource(R.string.jal_jeevan_mission),
            imageResId = R.drawable.s1,
            backgroundColor = Color(0xFFE0F7FA)
        ),
        Mission(
            title = stringResource(R.string.jal_shakti_abhiyan),
            imageResId = R.drawable.s2,
            backgroundColor = Color(0xFFE8F5E9)
        ),
        Mission(
            title = stringResource(R.string.swachh_bharat_abhiyan),
            imageResId = R.drawable.s3,
            backgroundColor = Color(0xFFFFF3E0)
        ),
        Mission(
            title = stringResource(R.string.national_water_mission),
            imageResId = R.drawable.s4,
            backgroundColor = Color(0xFFE3F2FD)
        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.explore_initiatives),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            //color = Color.White,
            color = Color(0xFF1565C0),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(dummyMissions) { mission ->
                MissionCard(mission = mission)
            }
        }
    }
}

// 💡 4. MODIFIED MissionCard Composable (Entire card is circular)
@Composable
fun MissionCard(mission: Mission) {
    Column( // Use Column as the container for the entire card
        modifier = Modifier
            .size(200.dp) // Fixed size for the circular card (e.g., 150dp diameter)
            .clip(CircleShape) // Clip the entire column to a circle
            .background(mission.backgroundColor) // Background color for the circle
            .clickable { /* TODO: Add navigation or action for mission click */ } // Make it clickable
            .padding(12.dp), // Inner padding for content within the circle
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Center content vertically
    ) {
        // Image at the top
        Image(
            painter = painterResource(id = mission.imageResId),
            contentDescription = mission.title,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp)),// Adjust image size inside the circle
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp)) // Space between image and title

        // Title
        Text(
            text = mission.title,
            // style is redundant if using explicit fontSize and fontWeight
            style = MaterialTheme.typography.bodyLarge, // Use bodyLarge style for better base
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp, // 💡 DECREASED FONT SIZE to fit within the small circle
            color = Color.Black,
            textAlign = TextAlign.Center, // ✅ Center aligned
            maxLines = 2, // ✅ Title wraps to two lines
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
            // ❌ REMOVED the redundant .padding(12.dp)
            // Keep only .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(2.dp)) // Small space for subtitle

    }
}