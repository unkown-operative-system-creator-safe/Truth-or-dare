package com.truthordare.app.data

data class QuestionTemplate(
    val id: String,
    val pattern: String,
    val type: QuestionType,
    val category: Category,
    val intensity: Intensity,
    val rating: ContentRating,
    val slots: List<String>,
    val combos: Int = 40,
    val requiresPartner: Boolean = false,
    val timerSuggestedSeconds: Int = 0
)

object Templates {
    val slots = mapOf(
        "body_part" to listOf("neck", "shoulder", "cheek", "jaw", "ear", "wrist", "collarbone", "forehead"),
        "duration" to listOf("10 seconds", "20 seconds", "30 seconds", "one minute", "two minutes"),
        "action" to listOf("slow dance", "hold hands", "stare deeply", "whisper softly", "trace circles"),
        "adjective" to listOf("honest", "bold", "sweet", "unexpected", "raw", "heartfelt"),
        "topic" to listOf("what you want tonight", "your secret fantasy", "your favorite memory of them", "a hidden craving"),
        "emotion" to listOf("confident", "nervous", "desired", "bold", "curious", "wanted"),
        "timeframe" to listOf("last week", "last month", "last year", "this morning", "last night"),
        "scenario" to listOf("no one would ever find out", "you had one night", "they asked first", "you were in charge"),
        "secret_type" to listOf("biggest crush", "wildest fantasy", "most embarrassing memory", "weirdest turn-on"),
        "direction" to listOf("left", "right", "across the circle", "diagonal"),
        "concept" to listOf("your happiest memory", "your dream vacation", "your comfort food", "your guilty pleasure")
    )

    fun all(): List<QuestionTemplate> = listOf(
        QuestionTemplate("bedroom_mild_1", "Kiss your partner on the {body_part} for {duration}.", QuestionType.DARE, Category.BEDROOM, Intensity.MILD, ContentRating.ADULT, listOf("body_part", "duration"), combos = 24, requiresPartner = true, timerSuggestedSeconds = 30),
        QuestionTemplate("bedroom_truth_1", "Tell the group about the {timeframe} you felt most {emotion}.", QuestionType.TRUTH, Category.BEDROOM, Intensity.MEDIUM, ContentRating.ADULT, listOf("timeframe", "emotion"), combos = 25),
        QuestionTemplate("nofear_truth_1", "Describe what you would do if {scenario}.", QuestionType.TRUTH, Category.NO_FEAR, Intensity.EXTREME, ContentRating.ADULT, listOf("scenario"), combos = 25),
        QuestionTemplate("risky_dare_1", "Send your partner a message describing {topic} without anyone else seeing it.", QuestionType.DARE, Category.RISKY, Intensity.MEDIUM, ContentRating.ADULT, listOf("topic"), combos = 20, requiresPartner = true),
        QuestionTemplate("nofear_dare_1", "Whisper your {secret_type} to the person on your {direction}.", QuestionType.DARE, Category.NO_FEAR, Intensity.EXTREME, ContentRating.ADULT, listOf("secret_type", "direction"), combos = 20),
        QuestionTemplate("bedroom_dare_2", "Give the person across from you a {adjective} compliment about their {body_part}.", QuestionType.DARE, Category.BEDROOM, Intensity.MILD, ContentRating.ADULT, listOf("adjective", "body_part"), combos = 20),
        QuestionTemplate("nofear_truth_2", "Name the one thing you would never admit about {topic}.", QuestionType.TRUTH, Category.NO_FEAR, Intensity.EXTREME, ContentRating.ADULT, listOf("topic"), combos = 20),
        QuestionTemplate("bedroom_dare_3", "Do a {duration} {action} while making eye contact with your partner.", QuestionType.DARE, Category.BEDROOM, Intensity.MEDIUM, ContentRating.ADULT, listOf("duration", "action"), combos = 30, requiresPartner = true, timerSuggestedSeconds = 60),
        QuestionTemplate("risky_dare_2", "Text the last person you flirted with about {topic}.", QuestionType.DARE, Category.RISKY, Intensity.EXTREME, ContentRating.ADULT, listOf("topic"), combos = 20),
        QuestionTemplate("nofear_dare_2", "Show the group a photo that represents {concept}.", QuestionType.DARE, Category.NO_FEAR, Intensity.MILD, ContentRating.SAFE, listOf("concept"), combos = 15)
    )
}
