package com.truthordare.app.data

import android.content.Context
import android.content.SharedPreferences

class SettingsRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("truth_or_dare_settings", Context.MODE_PRIVATE)

    fun getSettings(): UserSettings = UserSettings(
        playerCount = prefs.getInt("player_count", 4),
        turnOrder = TurnOrder.valueOf(prefs.getString("turn_order", TurnOrder.SEQUENTIAL.name) ?: TurnOrder.SEQUENTIAL.name),
        defaultGameMode = GameMode.valueOf(prefs.getString("default_game_mode", GameMode.MIX.name) ?: GameMode.MIX.name),
        timeLimitSeconds = prefs.getInt("time_limit_seconds", 45),
        enableCountdown = prefs.getBoolean("enable_countdown", true),
        allowAdultQuestions = prefs.getBoolean("allow_adult_questions", true),
        safeMode = prefs.getBoolean("safe_mode", false),
        minorMode = prefs.getBoolean("minor_mode", false),
        soundEnabled = prefs.getBoolean("sound_enabled", true),
        themeMode = ThemeMode.valueOf(prefs.getString("theme_mode", ThemeMode.DARK.name) ?: ThemeMode.DARK.name),
        reduceMotion = prefs.getBoolean("reduce_motion", false),
        strictNoDuplicateQuestions = prefs.getBoolean("strict_no_duplicate_questions", true),
        lockMinorMode = prefs.getBoolean("lock_minor_mode", true),
        enableHaptics = prefs.getBoolean("enable_haptics", true)
    )

    fun saveSettings(settings: UserSettings) {
        prefs.edit().apply {
            putInt("player_count", settings.playerCount)
            putString("turn_order", settings.turnOrder.name)
            putString("default_game_mode", settings.defaultGameMode.name)
            putInt("time_limit_seconds", settings.timeLimitSeconds)
            putBoolean("enable_countdown", settings.enableCountdown)
            putBoolean("allow_adult_questions", settings.allowAdultQuestions)
            putBoolean("safe_mode", settings.safeMode)
            putBoolean("minor_mode", settings.minorMode)
            putBoolean("sound_enabled", settings.soundEnabled)
            putString("theme_mode", settings.themeMode.name)
            putBoolean("reduce_motion", settings.reduceMotion)
            putBoolean("strict_no_duplicate_questions", settings.strictNoDuplicateQuestions)
            putBoolean("lock_minor_mode", settings.lockMinorMode)
            putBoolean("enable_haptics", settings.enableHaptics)
        }.apply()
    }
}
