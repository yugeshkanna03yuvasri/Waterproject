package com.example.watermonitoringsystem.datamodel

import android.content.Context
import com.google.gson.Gson

data class Village(
    val state: Map<String, String>,
    val district: Map<String, String>,
    val village: Map<String, String>,
    val lat: Double,
    val lng: Double

)
fun loadVillages(context: Context): List<Village> {
    val json = context.assets.open("villages.json")
        .bufferedReader()
        .use { it.readText() }

    return Gson().fromJson(json, Array<Village>::class.java).toList()
}