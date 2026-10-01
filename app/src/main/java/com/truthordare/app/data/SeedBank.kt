package com.truthordare.app.data

object SeedBank {
    fun all(): List<Question> = listOf(
        Question(1, "What is the first thing that attracted you to the person on your left?", QuestionType.TRUTH, Category.BEDROOM, Intensity.MILD, ContentRating.ADULT, requiresPartner = false),
        Question(2, "Describe your most memorable kiss in one sentence.", QuestionType.TRUTH, Category.BEDROOM, Intensity.MILD, ContentRating.ADULT),
        Question(3, "What outfit makes you feel most irresistible tonight?", QuestionType.TRUTH, Category.BEDROOM, Intensity.MILD, ContentRating.ADULT),
        Question(4, "Give your partner a 30-second neck kiss without using your hands.", QuestionType.DARE, Category.BEDROOM, Intensity.MEDIUM, ContentRating.ADULT, requiresPartner = true, timerSuggestedSeconds = 30),
        Question(5, "Whisper a secret you have never said aloud to your partner.", QuestionType.DARE, Category.BEDROOM, Intensity.MEDIUM, ContentRating.ADULT, requiresPartner = true),
        Question(6, "Remove one item of your partner's choice.", QuestionType.DARE, Category.BEDROOM, Intensity.MEDIUM, ContentRating.ADULT, requiresPartner = true),
        Question(7, "What is the one fantasy you have never admitted out loud?", QuestionType.TRUTH, Category.BEDROOM, Intensity.EXTREME, ContentRating.ADULT),
        Question(8, "If consequences did not exist, what would you do tonight?", QuestionType.TRUTH, Category.BEDROOM, Intensity.EXTREME, ContentRating.ADULT),
        Question(9, "Slide your hand onto your partner's thigh and leave it there for a full minute.", QuestionType.DARE, Category.RISKY, Intensity.MEDIUM, ContentRating.ADULT, requiresPartner = true, timerSuggestedSeconds = 60),
        Question(10, "Text your partner what you want later while someone else in the room watches you type.", QuestionType.DARE, Category.RISKY, Intensity.MEDIUM, ContentRating.ADULT, requiresPartner = true),
        Question(11, "Rank everyone in the room by who you would choose first.", QuestionType.TRUTH, Category.NO_FEAR, Intensity.EXTREME, ContentRating.ADULT),
        Question(12, "What is your honest body count?", QuestionType.TRUTH, Category.NO_FEAR, Intensity.EXTREME, ContentRating.ADULT),
        Question(13, "Show the last photo in your gallery.", QuestionType.DARE, Category.RISKY, Intensity.MILD, ContentRating.SAFE),
        Question(14, "Read your last text message out loud.", QuestionType.DARE, Category.RISKY, Intensity.MILD, ContentRating.SAFE),
        Question(15, "Impersonate someone in the room until they guess it.", QuestionType.DARE, Category.NO_FEAR, Intensity.MILD, ContentRating.SAFE),
        Question(16, "What is the most embarrassing thing you have ever texted someone?", QuestionType.TRUTH, Category.RISKY, Intensity.MEDIUM, ContentRating.ADULT),
        Question(17, "Reveal what you would do if your crush texted you at 2am.", QuestionType.TRUTH, Category.RISKY, Intensity.MEDIUM, ContentRating.ADULT),
        Question(18, "Do a slow dance with the person to your left for 20 seconds.", QuestionType.DARE, Category.BEDROOM, Intensity.MILD, ContentRating.ADULT, requiresPartner = true, timerSuggestedSeconds = 20),
        Question(19, "Tell the group the most intense thing you have ever wanted to do to someone.", QuestionType.TRUTH, Category.NO_FEAR, Intensity.EXTREME, ContentRating.ADULT),
        Question(20, "Give someone in the room a compliment they would never expect.", QuestionType.DARE, Category.NO_FEAR, Intensity.MILD, ContentRating.SAFE),
        Question(21, "What is your biggest turn-on that you would never admit in public?", QuestionType.TRUTH, Category.BEDROOM, Intensity.EXTREME, ContentRating.ADULT),
        Question(22, "Whisper a dirty secret into someone’s ear and then laugh it off.", QuestionType.DARE, Category.BEDROOM, Intensity.MEDIUM, ContentRating.ADULT, requiresPartner = true),
        Question(23, "Name the one person here you would be most likely to kiss tonight.", QuestionType.TRUTH, Category.BEDROOM, Intensity.MILD, ContentRating.ADULT),
        Question(24, "Describe the moment you first realized you were attracted to someone.", QuestionType.TRUTH, Category.BEDROOM, Intensity.MEDIUM, ContentRating.ADULT),
        Question(25, "Give the person across from you a bold compliment about their smile.", QuestionType.DARE, Category.NO_FEAR, Intensity.MILD, ContentRating.SAFE),
        Question(26, "Tell everyone what kind of touch you crave most.", QuestionType.TRUTH, Category.BEDROOM, Intensity.EXTREME, ContentRating.ADULT),
        Question(27, "Read the last message you sent that made you blush.", QuestionType.DARE, Category.RISKY, Intensity.MEDIUM, ContentRating.ADULT),
        Question(28, "Share your most embarrassing flirtation story.", QuestionType.TRUTH, Category.NO_FEAR, Intensity.MEDIUM, ContentRating.ADULT),
        Question(29, "Perform a dramatic slow-motion reveal for the room.", QuestionType.DARE, Category.NO_FEAR, Intensity.MILD, ContentRating.SAFE),
        Question(30, "Which of us in this room would be the best date?", QuestionType.TRUTH, Category.NO_FEAR, Intensity.MILD, ContentRating.SAFE)
    )
}
