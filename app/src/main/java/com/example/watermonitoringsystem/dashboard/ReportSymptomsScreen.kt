package com.example.watermonitoringsystem.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    var personName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var season by remember { mutableStateOf("Summer") }
    var reportsInArea by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.White
    ) { padding ->
        // Scrollable column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Report Your Symptoms",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D47A1),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Person Name
            OutlinedTextField(
                value = personName,
                onValueChange = { personName = it },
                label = { Text("Person Name", color = Color(0xFF0D47A1)) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1976D2),
                    unfocusedBorderColor = Color.Gray
                )
            )

            // Age
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Age of Patient", color = Color(0xFF0D47A1)) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1976D2),
                    unfocusedBorderColor = Color.Gray
                )
            )

            // Gender selection
            Text("Gender", fontWeight = FontWeight.SemiBold, color = Color(0xFF0D47A1))
            Row(verticalAlignment = Alignment.CenterVertically) {
                listOf("Male", "Female", "Other").forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        RadioButton(
                            selected = gender == option,
                            onClick = { gender = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF1976D2))
                        )
                        Text(option, color = Color.Black)
                    }
                }
            }

            // Duration of symptoms
            OutlinedTextField(
                value = duration,
                onValueChange = { duration = it },
                label = { Text("Duration of Symptoms (days)", color = Color(0xFF0D47A1)) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1976D2),
                    unfocusedBorderColor = Color.Gray
                )
            )

            // Symptoms list
            Text(
                text = "Select Symptoms",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0D47A1),
                modifier = Modifier.padding(top = 12.dp)
            )

            symptoms.forEach { symptom ->
                SymptomCard(
                    symptom = symptom,
                    isSelected = symptom.isSelected,
                    onClick = { viewModel.toggleSymptomSelection(symptom.id) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Submit button
            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Case Submitted Successfully!")
                        kotlinx.coroutines.delay(2000)
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
