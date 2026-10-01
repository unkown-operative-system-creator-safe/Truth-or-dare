package com.truthordare.app.data

enum class TurnOrder { SEQUENTIAL, RANDOM, MANUAL }
enum class ThemeMode { DARK, LIGHT, SYSTEM, AMOLED }
enum class FontSize { SMALL, MEDIUM, LARGE, XL }
enum class CardAnimation { FLIP, SLIDE, FADE, NONE }
enum class BackgroundStyle { SOLID, GRADIENT, BLUR }
enum class HapticLevel { OFF, LIGHT, MEDIUM, STRONG }
enum class ShuffleAlgorithm { PURE_RANDOM, WEIGHTED_BY_INTENSITY }
enum class QuestionSource { BUILTIN, CUSTOM, MIXED }
enum class HistoryRetention { DAYS_7, DAYS_30, DAYS_90, FOREVER }

data class UserSettings(
    val playerCount: Int = 4,
    val turnOrder: TurnOrder = TurnOrder.SEQUENTIAL,
    val defaultGameMode: GameMode = GameMode.MIX,
    val timeLimitSeconds: Int = 45,
    val enableCountdown: Boolean = true,
    val allowAdultQuestions: Boolean = true,
    val safeMode: Boolean = false,
    val minorMode: Boolean = false,
    val requireConsentPrompt: Boolean = true,
    val showSafeWordOnCard: Boolean = true,
    val showTimer: Boolean = true,
    val shakeToReroll: Boolean = false,
    val vibeMode: Boolean = true,
    val themeMode: ThemeMode = ThemeMode.DARK,
    val fontSize: FontSize = FontSize.MEDIUM,
    val cardAnimation: CardAnimation = CardAnimation.FLIP,
    val backgroundStyle: BackgroundStyle = BackgroundStyle.GRADIENT,
    val hapticLevel: HapticLevel = HapticLevel.MEDIUM,
    val shuffleAlgorithm: ShuffleAlgorithm = ShuffleAlgorithm.PURE_RANDOM,
    val questionSource: QuestionSource = QuestionSource.BUILTIN,
    val historyRetention: HistoryRetention = HistoryRetention.DAYS_30,
    val soundEnabled: Boolean = true,
    val voiceEnabled: Boolean = false,
    val highContrast: Boolean = false,
    val reduceMotion: Boolean = false,
    val twoDeviceSilentMode: Boolean = false,
    val countdownVibrateEachSecond: Boolean = true,
    val useImplicitConsent: Boolean = false,
    val allowCustomQuestions: Boolean = true,
    val blendQuestionTypes: Boolean = true,
    val keepPreviousQuestion: Boolean = false,
    val showCategoryBadges: Boolean = true,
    val autoAdvanceAfterTimer: Boolean = true,
    val requireSafeWordBeforePlay: Boolean = false,
    val pauseOnBackground: Boolean = true,
    val tapToReveal: Boolean = false,
    val hideAnswersFromObservers: Boolean = true,
    val allowSkipWithoutPenalty: Boolean = true,
    val showSessionHistory: Boolean = true,
    val persistentSessionState: Boolean = true,
    val enableHaptics: Boolean = true,
    val lowBatteryMode: Boolean = false,
    val darkModeByDefault: Boolean = true,
    val useRoundedCards: Boolean = true,
    val showPlayerNames: Boolean = true,
    val compactMode: Boolean = false,
    val keepScreenOnDuringGame: Boolean = true,
    val blurBackgroundBehindCard: Boolean = true,
    val autoShuffleOnRestart: Boolean = true,
    val promptBeforeLeaving: Boolean = true,
    val quickExitButtonVisible: Boolean = true,
    val questionPreviewBeforeStart: Boolean = false,
    val emojiFooter: Boolean = true,
    val strictNoDuplicateQuestions: Boolean = true,
    val useSeedBankFallbacks: Boolean = true,
    val lockMinorMode: Boolean = true,
    val scoringEnabled: Boolean = false,
    val showIntensityPills: Boolean = true,
    val cardGlowEnabled: Boolean = true
)

enum class GameMode { TRUTH, DARE, MIX }
