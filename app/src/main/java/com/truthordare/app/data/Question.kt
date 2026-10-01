package com.truthordare.app.data

enum class Category {
    BEDROOM,
    RISKY,
    NO_FEAR
}

enum class QuestionType {
    TRUTH,
    DARE
}

enum class Intensity(val level: Int) {
    MILD(1),
    MEDIUM(2),
    EXTREME(3)
}

enum class ContentRating {
    SAFE,
    ADULT
}

data class Question(
    val id: Int,
    val text: String,
    val type: QuestionType,
    val category: Category,
    val intensity: Intensity,
    val rating: ContentRating,
    val requiresPartner: Boolean = false,
    val timerSuggestedSeconds: Int = 0
)
