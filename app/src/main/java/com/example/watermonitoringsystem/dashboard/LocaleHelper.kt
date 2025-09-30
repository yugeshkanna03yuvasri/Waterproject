package com.example.watermonitoringsystem.dashboard

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import java.util.*

object LocaleHelper {
    fun wrapContext(context: Context, language: String): ContextWrapper {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources
        val config = resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
        } else {
            @Suppress("DEPRECATION")
            config.locale = locale
        }

        return ContextWrapper(context.createConfigurationContext(config))
    }
    fun saveLanguage(context: Context, lang: String) {
        val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        prefs.edit().putString("lang", lang).apply()
    }

    fun getSavedLanguage(context: Context?): String? {
        val prefs = context?.getSharedPreferences("settings", Context.MODE_PRIVATE)
        return prefs?.getString("lang", null)
    }

}
