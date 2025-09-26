package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileCreationScreen() {
    var step by remember { mutableStateOf(1) }
    var selectedRole by remember { mutableStateOf("") }

    // Form fields
    var aadhaar by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Light blue background
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (step == 1) {
            // Step 1: Role Selection
            Text(
                text = "Select Your Role",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            RoleButton("Volunteer", selectedRole == "Volunteer") {
                selectedRole = "Volunteer"
                step = 2
            }
            Spacer(modifier = Modifier.height(16.dp))
            RoleButton("ASHA Worker", selectedRole == "ASHA Worker") {
                selectedRole = "ASHA Worker"
                step = 2
            }
        } else if (step == 2) {
            // Step 2: Profile Details
            Text(
                text = "Fill Your Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = aadhaar,
                onValueChange = { aadhaar = it },
                label = { Text("Aadhaar Number") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
            )
            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Gender") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Save Profile Logic */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
            ) {
                Text("Create Profile", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))
            TextButton(onClick = { step = 1 }) {
                Text("← Back", color = Color(0xFF1565C0))
            }
        }
    }
}

@Composable
fun RoleButton(role: String, selected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) Color(0xFF1565C0) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = role,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (selected) Color.White else Color(0xFF1565C0)
            )
        }
    }
}
