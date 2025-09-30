//package com.example.watermonitoringsystem.ui
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.compose.ui.res.stringResource
//import com.example.watermonitoringsystem.dashboard.ContactHealthOfficials
//import com.example.watermonitoringsystem.dashboard.FeatureSection
//import com.example.watermonitoringsystem.dashboard.HealthBannerContent
//import com.example.watermonitoringsystem.dashboard.MissionsSection
//import com.example.watermonitoringsystem.dashboard.getSavedVillage
//import com.example.watermonitoringsystem.datamodel.HealthContactObj
//import com.example.watermonitoringsystem.R
//import com.example.watermonitoringsystem.dashboard.getCurrentLangCode
//
//@Composable
//fun HomeScreen(navController: NavController ) {
//    val context = LocalContext.current
//    val currentLangCode = getCurrentLangCode()
//    val savedVillageData = getSavedVillage(context)
//    val localizedVillageName = remember(savedVillageData, currentLangCode) {
//        if (savedVillageData != null) {
//            // Use the same localization logic: check current language, then fallback to English
//            savedVillageData.village[currentLangCode]
//                ?: savedVillageData.village["en"]
//                ?: savedVillageData.village.values.firstOrNull()
//                ?: ""
//        } else {
//            ""
//        }
//    }
//    val canonicalVillageName = remember(savedVillageData) {
//        // This MUST always be the English key from the map
//        savedVillageData?.village?.get("en") ?: ""
//    }
//    val selectedVillageName by remember {
//        mutableStateOf(localizedVillageName)
//    }
//    Surface(modifier = Modifier.fillMaxSize(),
//        color = Color.White
//    )
//        {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//        ) {
//            // Top Header
//            HealthBannerContent(navController = navController)
//
//            //Spacer(modifier = Modifier.height(8.dp))
//
//            // Feature Cards Grid
//            FeatureSection(navController = navController)
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            MissionsSection()
//
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Text(
//                text = stringResource(R.string.health_contacts_for, localizedVillageName),
//                style = MaterialTheme.typography.headlineSmall,
//                fontWeight = FontWeight.Bold,
//                //color = Color.White,
//                color = Color(0xFF1565C0),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 12.dp)
//            )
//
//            if (canonicalVillageName.isNotEmpty()) {
//                // 2. Call the Contact composable with the saved village name
//                ContactHealthOfficials(
//                    selectedVillage = selectedVillageName,
//                    healthContacts = HealthContactObj.healthContacts
//                )
//            } else {
//                // Prompt to select a village if none is saved
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCCBC)),
//                    elevation = CardDefaults.cardElevation(4.dp)
//                ) {
//                    Text(
//                        "⚠️ Please select your village in the section above to see relevant contact information.",
//                        modifier = Modifier.padding(16.dp),
//                        color = Color.Black.copy(alpha = 0.8f)
//                    )
//                }
//            }
//
//        }
//    }
//}\

package com.example.watermonitoringsystem.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import com.example.watermonitoringsystem.dashboard.ContactHealthOfficials
import com.example.watermonitoringsystem.dashboard.FeatureSection
import com.example.watermonitoringsystem.dashboard.HealthBannerContent
import com.example.watermonitoringsystem.dashboard.MissionsSection
import com.example.watermonitoringsystem.dashboard.getSavedVillage
import com.example.watermonitoringsystem.datamodel.HealthContactObj
import com.example.watermonitoringsystem.R
import com.example.watermonitoringsystem.dashboard.getCurrentLangCode // Ensure this is imported

@Composable
fun HomeScreen(navController: NavController ) {
    val context = LocalContext.current
    val currentLangCode = getCurrentLangCode()
    val savedVillageData = getSavedVillage(context)

    // Localized name: Used for display in the header (e.g., "रंगिया")
    val localizedVillageName = remember(savedVillageData, currentLangCode) {
        if (savedVillageData != null) {
            // Check current language, then fallback to English
            savedVillageData.village[currentLangCode]
                ?: savedVillageData.village["en"]
                ?: savedVillageData.village.values.firstOrNull()
                ?: ""
        } else {
            ""
        }
    }

    // Canonical name: Used for LOOKUP/FILTERING in your contact data (MUST be "Rangia")
    val canonicalVillageName = remember(savedVillageData) {
        // We assume 'en' is the lookup key in HealthContactObj.healthContacts
        savedVillageData?.village?.get("en") ?: ""
    }

    Surface(modifier = Modifier.fillMaxSize(),
        color = Color.White
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Header
            HealthBannerContent(navController = navController)

            // Feature Cards Grid
            FeatureSection(navController = navController)

            Spacer(modifier = Modifier.height(24.dp))

            MissionsSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Text Header: Uses the localized name for the user's language
            Text(
                text = stringResource(R.string.health_contacts_for, localizedVillageName),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            // Contact Section: Uses the canonical name for lookup
            if (canonicalVillageName.isNotEmpty()) {
                // PASS THE CANONICAL NAME HERE for the internal data lookup
                ContactHealthOfficials(
                    selectedVillage = canonicalVillageName, // <--- CORRECTED!
                    healthContacts = HealthContactObj.healthContacts
                )
            } else {
                // Prompt to select a village if none is saved
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCCBC)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        "⚠️ Please select your village in the section above to see relevant contact information.",
                        modifier = Modifier.padding(16.dp),
                        color = Color.Black.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
