package com.truthordare.app.data

data class SessionState(
    val category: Category = Category.BEDROOM,
    val currentQuestion: Question? = null,
    val totalQuestions: Int = 0,
    val answered: Int = 0,
    val players: List<String> = listOf("Player 1", "Player 2", "Player 3"),
    val isMinorMode: Boolean = false
)
