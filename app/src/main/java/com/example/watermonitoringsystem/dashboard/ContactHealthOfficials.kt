package com.example.watermonitoringsystem.dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.watermonitoringsystem.datamodel.HealthContact
import androidx.compose.ui.res.stringResource
import com.example.watermonitoringsystem.R

@Composable
fun ContactHealthOfficials(
    selectedVillage: String,
    healthContacts: List<HealthContact>
) {
    val selectedContact = healthContacts.find { it.village == selectedVillage }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        selectedContact?.let { contact ->
            ContactCard(stringResource(R.string.asha_worker), contact.ashaWorker, contact.ashaPhone)
            ContactCard(stringResource(R.string.phc), contact.phcName, contact.phcPhone)
            ContactCard(stringResource(R.string.doctor), contact.doctorName, contact.doctorPhone)
        } ?: Text(stringResource(R.string.no_contacts_found, selectedVillage))
    }
}

@Composable
fun ContactCard(role: String, name: String, phone: String) {
    val context = LocalContext.current

    val callAction: () -> Unit = {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phone")
            }
            context.startActivity(intent)
        } catch (e: Exception) { }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "$role: $name",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = callAction)
                    .padding(vertical = 4.dp)) {
                Icon(
                    Icons.Default.Call,
                    contentDescription = stringResource(R.string.call),
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = phone,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF2E7D32)
                )
            }
        }
    }
}
