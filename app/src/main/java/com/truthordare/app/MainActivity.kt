package com.truthordare.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.truthordare.app.ui.navigation.TruthOrDareNavGraph
import com.truthordare.app.ui.theme.TruthOrDareTheme
import com.truthordare.app.viewmodel.SettingsViewModel
import com.truthordare.app.viewmodel.SettingsViewModelFactory

class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels { SettingsViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val settings by settingsViewModel.settings.collectAsState()
            TruthOrDareTheme(settings = settings) {
                TruthOrDareNavGraph(settingsViewModel = settingsViewModel)
            }
        }
    }
}
