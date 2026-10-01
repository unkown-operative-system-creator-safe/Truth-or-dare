package com.truthordare.app.data

data class GameHistory(
    val completedAt: Long = System.currentTimeMillis(),
    val category: Category = Category.BEDROOM,
    val totalQuestions: Int = 0,
    val answered: Int = 0
)
