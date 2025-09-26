package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitoringsystem.R

// 🔹 Missions Section
@Composable
fun MissionsSection() {
    val missions = listOf(
        Triple("Jal Jeevan Mission", "Ensuring access to safe drinking water across rural India.", R.drawable.sih2),
        Triple("Jal Shakti Abhiyan", "Focused on water conservation and sustainable water management across India.", R.drawable.sih1),
        Triple("Swachh Bharat Mission", "Promoting cleanliness and sanitation for healthier living.", R.drawable.sih4),
        Triple("National Health Mission", "Strengthening healthcare facilities in rural and urban areas.", R.drawable.sih3)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Know Other Missions",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1565C0),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
        ) {
            missions.forEach { (title, desc, imageRes) ->
                MissionCardSquare(title, desc, imageRes, Modifier.padding(end = 16.dp))
            }
        }
    }
}

// 🔹 Improved Mission Card
@Composable
fun MissionCardSquare(title: String, desc: String, imageRes: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(width = 240.dp, height = 280.dp) // Bigger card for better visibility
            .padding(6.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)) // Light blue background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF0D47A1),
                textAlign = TextAlign.Center
            )

            // Larger Image
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(width = 250.dp , height = 120.dp)
                    .clip(RoundedCornerShape(16.dp))
                     // Bigger size for visibility

            )

            // Description
            Text(
                desc,
                fontSize = 13.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


//package com.example.watermonitoringsystem.dashboard
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.watermonitoringsystem.R
//
//// 🔹 Missions Section
//@Composable
//fun MissionsSection() {
//    val missions = listOf(
//        Triple("Jal Jeevan Mission", "Ensuring access to safe drinking water across rural India.", R.drawable.sih2),
//        Triple("Jal Shakti Abhiyan", "Focused on water conservation and sustainable water management across India.", R.drawable.sih1),
//        Triple("Swachh Bharat Mission", "Promoting cleanliness and sanitation for healthier living.", R.drawable.sih4),
//        Triple("National Health Mission", "Strengthening healthcare facilities in rural and urban areas.", R.drawable.sih3)
//    )
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//        Text(
//            text = "Know Other Missions",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color(0xFF1565C0),
//            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .horizontalScroll(rememberScrollState())
//                .padding(horizontal = 12.dp)
//        ) {
//            missions.forEach { (title, desc, imageRes) ->
//                MissionCardSquare(title, desc, imageRes, Modifier.padding(end = 16.dp))
//            }
//        }
//    }
//}
//
//// 🔹 Mission Card with Rectangular Image + Pastel Theme
//@Composable
//fun MissionCardSquare(title: String, desc: String, imageRes: Int, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier
//            .size(width = 240.dp, height = 300.dp) // taller for image + text
//            .padding(6.dp),
//        shape = RoundedCornerShape(20.dp),
//        elevation = CardDefaults.cardElevation(8.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF3FE)) // Softer pastel blue
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(12.dp),
//            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Title
//            Text(
//                text = title,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp,
//                color = Color(0xFF0D47A1),
//                textAlign = TextAlign.Center,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            // Rectangular Image with rounded corners
//            Image(
//                painter = painterResource(id = imageRes),
//                contentDescription = title,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(130.dp) // rectangular shape
//                    .clip(RoundedCornerShape(16.dp))
//            )
//
//            // Description
//            Text(
//                text = desc,
//                fontSize = 13.sp,
//                color = Color(0xFF424242),
//                textAlign = TextAlign.Center,
//                lineHeight = 18.sp,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 8.dp)
//            )
//        }
//    }
//}

