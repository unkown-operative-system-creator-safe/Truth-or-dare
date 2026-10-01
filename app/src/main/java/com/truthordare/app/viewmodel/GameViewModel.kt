package com.truthordare.app.viewmodel

import android.app.Application
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.truthordare.app.data.Category
import com.truthordare.app.data.GameMode
import com.truthordare.app.data.GameHistory
import com.truthordare.app.data.Question
import com.truthordare.app.data.QuestionEngine
import com.truthordare.app.data.SessionQueue
import com.truthordare.app.data.SessionState
import com.truthordare.app.data.SettingsRepository
import com.truthordare.app.data.UserSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
data class GameUiState(
    val phase: ScreenPhase = ScreenPhase.AGE_VERIFY,
    val category: Category = Category.BEDROOM,
    val settings: UserSettings = UserSettings(),
    val currentQuestion: Question? = null,
    val currentPlayer: String = "Player 1",
    val timeRemaining: Int = 45,
    val timerRunning: Boolean = false,
    val showConsentDialog: Boolean = false,
    val showSafeWordDialog: Boolean = false,
    val errorMessage: String? = null,
    val sessionHistory: List<GameHistory> = emptyList()
)

enum class ScreenPhase { AGE_VERIFY, HOME, CATEGORY, SETUP, GAME, SUMMARY }

class GameViewModel(application: Application) : ViewModel() {
    private val repository = SettingsRepository(application)
    private val _uiState = MutableStateFlow(GameUiState(settings = repository.getSettings()))
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()
    private var queue: SessionQueue? = null

    fun verifyAge() {
        _uiState.update { it.copy(phase = ScreenPhase.HOME) }
    }

    fun startSetup(category: Category) {
        val settings = _uiState.value.settings
        val pool = QuestionEngine.all.filter {
            it.category == category &&
                (!settings.minorMode || it.rating.name == "SAFE")
        }
        queue = SessionQueue(pool)
        val question = queue?.next()
        _uiState.update {
            it.copy(
                phase = ScreenPhase.SETUP,
                category = category,
                currentQuestion = question,
                timeRemaining = settings.timeLimitSeconds
            )
        }
    }

    fun configureSession(category: Category, mode: GameMode = GameMode.MIX) {
        val settings = _uiState.value.settings
        val pool = QuestionEngine.all.filter {
            it.category == category &&
                (!settings.minorMode || it.rating.name == "SAFE") &&
                (mode == GameMode.MIX || (mode == GameMode.TRUTH && it.type.name == "TRUTH") || (mode == GameMode.DARE && it.type.name == "DARE"))
        }
        queue = SessionQueue(pool)
        _uiState.update {
            it.copy(
                phase = ScreenPhase.GAME,
                category = category,
                currentQuestion = queue?.next(),
                timeRemaining = settings.timeLimitSeconds,
                currentPlayer = "Player 1"
            )
        }
    }

    fun nextQuestion() {
        val next = queue?.next()
        _uiState.update {
            it.copy(
                currentQuestion = next,
                timeRemaining = it.settings.timeLimitSeconds,
                currentPlayer = if (it.currentPlayer == "Player 1") "Player 2" else "Player 1"
            )
        }
    }

    fun skip() {
        nextQuestion()
    }

    fun reroll() {
        nextQuestion()
    }

    fun startTimer() {
        _uiState.update { it.copy(timerRunning = true) }
        viewModelScope.launch {
            while (_uiState.value.timerRunning && _uiState.value.timeRemaining > 0) {
                kotlinx.coroutines.delay(1000)
                _uiState.update { state -> state.copy(timeRemaining = state.timeRemaining - 1) }
            }
            _uiState.update { it.copy(timerRunning = false) }
        }
    }

    fun pauseTimer() {
        _uiState.update { it.copy(timerRunning = false) }
    }

    fun resetTimer() {
        _uiState.update { it.copy(timeRemaining = it.settings.timeLimitSeconds, timerRunning = false) }
    }

    fun markCompleted() {
        val history = _uiState.value.sessionHistory + GameHistory(
            category = _uiState.value.category,
            totalQuestions = 1,
            answered = 1
        )
        _uiState.update { it.copy(sessionHistory = history) }
    }

    fun endSession() {
        _uiState.update { it.copy(phase = ScreenPhase.SUMMARY) }
    }

    fun updateSettings(settings: UserSettings) {
        repository.saveSettings(settings)
        _uiState.update { it.copy(settings = settings) }
    }

    fun updateCurrentSettings(transform: (UserSettings) -> UserSettings) {
        val updated = transform(_uiState.value.settings)
        updateSettings(updated)
    }
}
