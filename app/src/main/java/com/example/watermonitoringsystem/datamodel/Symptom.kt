package com.example.watermonitoringsystem.datamodel

import com.google.gson.annotations.SerializedName
data class Symptom(
    val id: Int,
    val name: String,
    val isSelected: Boolean = false
)

data class DiseaseResponse(
    @SerializedName("diseases")
    val diseases: List<String>
)
