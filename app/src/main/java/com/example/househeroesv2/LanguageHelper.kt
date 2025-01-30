@file:Suppress("DEPRECATION")

package com.example.househeroesv2

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
//import android.os.Build
import java.util.Locale

object LanguageHelper {
    private const val PREFS_NAME = "app_prefs"
    private const val LANGUAGE_KEY = "language"

    fun setLocale(activity: Activity, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(activity.resources.configuration)
        config.setLocale(locale)

        activity.baseContext.resources.updateConfiguration(config, activity.baseContext.resources.displayMetrics)

        val prefs = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(LANGUAGE_KEY, languageCode).apply()

        activity.recreate()
    }

    fun loadLocale(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(LANGUAGE_KEY, "en") ?: "en" // Default to English
    }
}



