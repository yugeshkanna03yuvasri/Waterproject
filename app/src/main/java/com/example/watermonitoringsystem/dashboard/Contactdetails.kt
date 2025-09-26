package com.example.watermonitoringsystem.dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
// For WhatsApp, you can use a custom vector if needed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactScreenCards() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Contact Health Officials",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Grid Layout (2x2)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ContactCard(
                icon = Icons.Default.Call,
                title = "Helpline",
                subtitle = "1800-123-456",
                bgColor = Color(0xFFB3D1EE)
            ) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:1800123456"))
                context.startActivity(intent)
            }

            ContactCard(
                icon = Icons.Default.Email,
                title = "Email",
                subtitle = "asha-support@health.org",
                bgColor = Color(0xFFB3D1EE)
            ) {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:asha-support@health.org")
                }
                context.startActivity(intent)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ContactCard(
                icon = Icons.Default.Call,
                title = "District Officer",
                subtitle = "+91-9876543210",
                bgColor = Color(0xFFB3D1EE)
            ) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919876543210"))
                context.startActivity(intent)
            }

            ContactCard(
                icon = Icons.Default.Chat, // replace with WhatsApp vector if available
                title = "WhatsApp",
                subtitle = "+91-9123456789",
                bgColor = Color(0xFFB3D1EE)
            ) {
                val url = "https://wa.me/919123456789"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun ContactCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    bgColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(140.dp)
            .clickable { onClick() },
        shape = androidx.compose.foundation.shape.RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF1565C0),
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color(0xFF0D47A1))
            Text(subtitle, fontSize = 12.sp, color = Color.DarkGray, maxLines = 1)
        }
    }
}
