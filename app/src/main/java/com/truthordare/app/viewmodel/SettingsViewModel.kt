package com.truthordare.app.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.truthordare.app.data.SettingsRepository
import com.truthordare.app.data.UserSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : ViewModel() {
    private val repository = SettingsRepository(application)
    private val _settings = MutableStateFlow(repository.getSettings())
    val settings: StateFlow<UserSettings> = _settings.asStateFlow()

    fun updateSetting(transform: (UserSettings) -> UserSettings) {
        viewModelScope.launch {
            val updated = transform(_settings.value)
            repository.saveSettings(updated)
            _settings.value = updated
        }
    }

    fun resetAll() {
        val defaultSettings = UserSettings()
        repository.saveSettings(defaultSettings)
        _settings.value = defaultSettings
    }
}

class SettingsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
