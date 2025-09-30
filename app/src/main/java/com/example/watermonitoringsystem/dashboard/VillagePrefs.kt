package com.example.watermonitoringsystem.dashboard

import android.content.Context
import com.example.watermonitoringsystem.datamodel.Village
import com.google.gson.Gson
import java.lang.Exception // Import to ensure catch block works

const val PREFS_NAME = "settings"
const val VILLAGE_KEY = "selected_village_json"

fun saveSelectedVillage(context: Context, village: Village) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val gson = Gson()

    // FIX: Changed 'villages' (which is undefined) to 'village'
    val villageJson = gson.toJson(village)

    prefs.edit()
        .putString(VILLAGE_KEY, villageJson)
        .apply()
}

fun getSavedVillage(context: Context): Village? {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val villageJson = prefs.getString(VILLAGE_KEY, null)

    // 1. Check if the JSON string exists
    if (villageJson.isNullOrEmpty()) {
        return null
    }

    // 2. Deserialize the JSON string back into a Village object
    val gson = Gson()
    return try {
        // Correctly deserialize using Gson
        gson.fromJson(villageJson, Village::class.java)
    } catch (e: Exception) {
        // Log error and return null if parsing fails
        e.printStackTrace()
        null
    }

}
