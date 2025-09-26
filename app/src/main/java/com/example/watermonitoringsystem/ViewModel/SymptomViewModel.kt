package com.example.watermonitoringsystem.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.watermonitoringsystem.datamodel.Symptom

class SymptomViewModel() : ViewModel() {
    var symptoms by mutableStateOf<List<Symptom>>(emptyList())
        private set

    init {
        // Initialize with a hardcoded list of symptoms
        symptoms = listOf(
            Symptom(id = 0, name = "Fever", isSelected = false),
            Symptom(id = 1, name = "Nausea", isSelected = false),
            Symptom(id = 2, name = "Vomiting", isSelected = false),
            Symptom(id = 3, name = "Diarrhea", isSelected = false),
            Symptom(id = 4, name = "Abdominal pain", isSelected = false),
            Symptom(id = 5, name = "Jaundice", isSelected = false),
            Symptom(id = 6, name = "Headache", isSelected = false),
            Symptom(id = 7, name = "Fatigue", isSelected = false),
            Symptom(id = 8, name = "Loss of appetite", isSelected = false),
            Symptom(id = 9, name = "Rash", isSelected = false)
        )
    }

    fun toggleSymptomSelection(symptomId: Int) {
        symptoms = symptoms.map {
            if (it.id == symptomId) it.copy(isSelected = !it.isSelected) else it
        }
    }
}