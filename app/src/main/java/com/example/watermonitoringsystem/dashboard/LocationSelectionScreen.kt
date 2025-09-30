package com.example.watermonitoringsystem.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.watermonitoringsystem.datamodel.loadVillages

private fun getLocalizedName(nameMap: Map<String, String>, langCode: String): String {
    return nameMap[langCode] ?: nameMap["en"] ?: nameMap.values.firstOrNull() ?: ""
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSelectionScreen(navController: NavController) {
    val context = LocalContext.current
    val villages = remember { loadVillages(context) }

    // Get the current language code
    val currentLangCode = getCurrentLangCode()

    var selectedState by remember { mutableStateOf("") }
    var selectedDistrict by remember { mutableStateOf("") }
    var selectedVillage by remember { mutableStateOf("") }

    var showMessage by remember { mutableStateOf(false) }

    // --- REQUIRED FIXES: Use the localization logic for filtering and mapping ---

    // 1. Get a list of localized State names for the dropdown options
    val states = remember(villages, currentLangCode) {
        villages
            .map { it.state }
            .distinct()
            .map { getLocalizedName(it, currentLangCode) }
    }

    // 2. Filter villages based on the selected localized State name, and then get localized District names
    val districts = remember(villages, selectedState, currentLangCode) {
        if (selectedState.isEmpty()) {
            emptyList()
        } else {
            // Find the original state map corresponding to the localized name
            val originalStateMap = villages.firstOrNull {
                getLocalizedName(it.state, currentLangCode) == selectedState
            }?.state

            // Filter by the original state map (or just the selectedState if map comparison is complex)
            villages
                .filter { getLocalizedName(it.state, currentLangCode) == selectedState }
                .map { it.district }
                .distinct()
                .map { getLocalizedName(it, currentLangCode) }
        }
    }

    // 3. Filter villages based on selected localized State and District names
    val filteredVillageObjects = remember(villages, selectedState, selectedDistrict, currentLangCode) {
        if (selectedState.isEmpty() || selectedDistrict.isEmpty()) {
            emptyList()
        } else {
            villages.filter {
                getLocalizedName(it.state, currentLangCode) == selectedState &&
                        getLocalizedName(it.district, currentLangCode) == selectedDistrict
            }
        }
    }

    // Map the filtered Village objects to their localized names for the final dropdown
    val villageNamesList = filteredVillageObjects.map { getLocalizedName(it.village, currentLangCode) }
    // --------------------------------------------------------------------------

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Select Address", color = Color.White, fontSize = 20.sp) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF1565C0)
                )
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Dropdown 1: State
            DropdownMenuBox("Select State", selectedState, states) {
                selectedState = it
                selectedDistrict = ""
                selectedVillage = ""
                showMessage = false
            }

            if (selectedState.isNotEmpty()) {
                // Dropdown 2: District
                DropdownMenuBox("Select District", selectedDistrict, districts) {
                    selectedDistrict = it
                    selectedVillage = ""
                    showMessage = false
                }
            }

            if (selectedDistrict.isNotEmpty()) {
                // Dropdown 3: Village
                DropdownMenuBox("Select Village", selectedVillage, villageNamesList) {
                    selectedVillage = it
                    showMessage = false
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // ✅ Message above button
            if (showMessage) {
                Text(
                    "✅ Address saved. Thank you!",
                    color = Color(0xFF2E7D32), // green
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    if (selectedVillage.isNotEmpty()) {
                        // FIX: Find the original Village object using the localized name
                        val v = filteredVillageObjects.first { getLocalizedName(it.village, currentLangCode) == selectedVillage }
                        saveSelectedVillage(context, v)
                        showMessage = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)),
                enabled = selectedVillage.isNotEmpty()
            ) {
                Text("Save Address", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Composable
fun DropdownMenuBox(label: String, value: String, options: List<String>, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = true },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF1565C0)
            )
        ) {
            Text(
                text = if (value.isEmpty()) label else value,
                fontSize = 15.sp,
                color = if (value.isEmpty()) Color.Gray else Color.Black
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
                .wrapContentWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, fontSize = 15.sp, color = Color.Black) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}