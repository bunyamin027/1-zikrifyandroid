package com.bunyamin027.zikrify.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.bunyamin027.zikrify.model.Zikir
import com.bunyamin027.zikrify.model.ZikirData

class CounterViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("ZikrifyPrefs", Context.MODE_PRIVATE)

    // General
    var isEnglish = sharedPreferences.getString("appLanguage", "tr") == "en"
    val appLanguage = MutableStateFlow(sharedPreferences.getString("appLanguage", "tr") ?: "tr")

    // Settings
    val hapticEnabled = MutableStateFlow(sharedPreferences.getBoolean("hapticEnabled", true))
    val soundEnabled = MutableStateFlow(sharedPreferences.getBoolean("soundEnabled", false))
    val autoReset = MutableStateFlow(sharedPreferences.getBoolean("autoReset", false))
    val fontSizeMultiplier = MutableStateFlow(sharedPreferences.getFloat("fontSizeMultiplier", 1.0f))
    val selectedSoundType = MutableStateFlow(sharedPreferences.getString("selectedSoundType", "click") ?: "click")

    // Stats
    val dailyTotalGoal = MutableStateFlow(sharedPreferences.getInt("dailyTotalGoal", 1000))
    val totalToday = MutableStateFlow(sharedPreferences.getInt("totalToday", 0))
    val streak = MutableStateFlow(sharedPreferences.getInt("streak", 0))
    val totalAllTime = MutableStateFlow(sharedPreferences.getInt("totalAllTime", 0))
    val earnedBadges = MutableStateFlow(sharedPreferences.getStringSet("earnedBadges", emptySet()) ?: emptySet())

    private val _count = MutableStateFlow(sharedPreferences.getInt("count", 0))
    val count: StateFlow<Int> = _count.asStateFlow()

    private val _targetCount = MutableStateFlow(sharedPreferences.getInt("target", 33))
    val targetCount: StateFlow<Int> = _targetCount.asStateFlow()

    private val _selectedZikir = MutableStateFlow(ZikirData.allZikirler.first())
    val selectedZikir: StateFlow<Zikir> = _selectedZikir.asStateFlow()

    private val vibrator: Vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = application.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        application.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    val progress: Float
        get() = if (_targetCount.value > 0) {
            (_count.value.toFloat() / _targetCount.value.toFloat()).coerceIn(0f, 1f)
        } else {
            0f
        }

    val isCompleted: Boolean
        get() = _count.value >= _targetCount.value && _targetCount.value > 0

    fun increment() {
        if (!isCompleted) {
            _count.value += 1
            totalToday.value += 1
            totalAllTime.value += 1
            saveData()
            triggerHapticFeedback()
        }
    }

    fun reset() {
        _count.value = 0
        saveData()
    }

    fun selectZikir(zikir: Zikir) {
        _selectedZikir.value = zikir
        _count.value = 0
        saveData()
    }

    fun setTarget(target: Int) {
        _targetCount.value = target
        saveData()
    }

    private fun saveData() {
        sharedPreferences.edit()
            .putInt("count", _count.value)
            .putInt("target", _targetCount.value)
            .putInt("dailyTotalGoal", dailyTotalGoal.value)
            .putInt("totalToday", totalToday.value)
            .putInt("streak", streak.value)
            .putInt("totalAllTime", totalAllTime.value)
            .putStringSet("earnedBadges", earnedBadges.value)
            .apply()
    }

    fun saveSettings() {
        sharedPreferences.edit()
            .putBoolean("hapticEnabled", hapticEnabled.value)
            .putBoolean("soundEnabled", soundEnabled.value)
            .putBoolean("autoReset", autoReset.value)
            .putFloat("fontSizeMultiplier", fontSizeMultiplier.value)
            .putString("selectedSoundType", selectedSoundType.value)
            .putString("appLanguage", appLanguage.value)
            .apply()
        isEnglish = appLanguage.value == "en"
    }

    private fun triggerHapticFeedback() {
        if (hapticEnabled.value && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(50)
            }
        }
    }
}
