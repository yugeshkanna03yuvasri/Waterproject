package com.example.watermonitoringsystem.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.watermonitoringsystem.R


@Composable
fun SchemesSection() {
    val schemes = listOf(
        Scheme("Jal Jeevan Mission", R.drawable.sih2),
        Scheme("Jal Shakthi Abhiyan", R.drawable.sih1),
        Scheme("Swachh Bharat Mission", R.drawable.sih4),
        Scheme("National Rural Health Mission", R.drawable.sih3),
        //Scheme("Rural Sanitation Programme", R.drawable.sanitation)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
//        items(schemes.size) { index ->
//            SchemeCard(scheme = schemes[index])
//        }
        items(schemes) { scheme ->
            SchemeCard(scheme = scheme)
        }
    }
}

data class Scheme(val title: String, val imageRes: Int)

@Composable
fun SchemeCard(scheme: Scheme) {
    var visible by remember { mutableStateOf(false) }

    // Start animation when the card enters composition
    LaunchedEffect(Unit) {
        visible = true
    }
    val imageId = try {
        scheme.imageRes
    } catch (e: Exception) {
        R.drawable.healthcare_banner // add a default image to your resources
    }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {

                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = scheme.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = scheme.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1565C0),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
//}
