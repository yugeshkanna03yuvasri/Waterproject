package com.example.watermonitoringsystem.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.watermonitoringsystem.ViewModel.SymptomViewModel
import com.example.watermonitoringsystem.datamodel.Symptom
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportSymptomsScreen(viewModel: SymptomViewModel, navController: NavController) {
    val symptoms = viewModel.symptoms
    var additionalInfo by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Select Your Symptoms",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // ✅ Make symptom list scrollable
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(symptoms) { symptom ->
                    SymptomCard(
                        symptom = symptom,
                        isSelected = symptom.isSelected,
                        onClick = { viewModel.toggleSymptomSelection(symptom.id) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Additional info with proper text color
            OutlinedTextField(
                value = additionalInfo,
                onValueChange = { additionalInfo = it },
                label = { Text("Describe your symptoms (optional)") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1976D2),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFF1976D2),
                    focusedLabelColor = Color(0xFF1976D2),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Submit Button always visible
            Button(
                onClick = {
                    //navController.popBackStack()
                    scope.launch {
                        snackbarHostState.showSnackbar("Data Submitted Successfully!")
                        kotlinx.coroutines.delay(2000)

                        // then close page
                        navController.popBackStack()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1976D2),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Submit", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun SymptomCard(symptom: Symptom, isSelected: Boolean, onClick: () -> Unit) {
    // ✅ Slightly darker light blue
    val targetColor = if (isSelected) Color(0xFFBBDEFB) else Color(0xFFF5F5F5)
    val animatedColor by animateColorAsState(targetValue = targetColor, label = "cardAnim")

    val icon = when (symptom.name) {
        "Fever" -> Icons.Default.Thermostat
        "Nausea" -> Icons.Default.Sick
        "Vomiting" -> Icons.Default.MoodBad
        "Diarrhea" -> Icons.Default.WaterDrop
        "Abdominal pain" -> Icons.Default.MedicalServices
        "Jaundice" -> Icons.Default.Brightness5
        "Headache" -> Icons.Default.Troubleshoot
        "Fatigue" -> Icons.Default.Hotel
        "Loss of appetite" -> Icons.Default.NoMeals
        "Rash" -> Icons.Default.Spa
        else -> Icons.Default.Check
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(animatedColor)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = symptom.name,
                tint = Color(0xFF1976D2)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = symptom.name,
                fontSize = 16.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = Color(0xFF0D47A1)
            )
        }
    }
}
