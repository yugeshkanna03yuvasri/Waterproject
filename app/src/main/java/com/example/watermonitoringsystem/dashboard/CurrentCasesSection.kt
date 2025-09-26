package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CurrentCasesSection(
    choleraCases: Int,
    choleraLocation: String,
    typhoidCases: Int,
    typhoidLocation: String
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "📊 Current Cases - Assam",
            fontSize = 18.sp,
            color = Color(0xFF1976D2)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            CaseCard(disease = "Cholera", cases = choleraCases, location = choleraLocation)
            CaseCard(disease = "Typhoid", cases = typhoidCases, location = typhoidLocation)
        }
    }
}

@Composable
fun CaseCard(disease: String, cases: Int, location: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(90.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "$disease: $cases cases", fontSize = 14.sp, color = Color.Black)
            Text(text = location, fontSize = 12.sp, color = Color.Gray)
        }
    }
}
