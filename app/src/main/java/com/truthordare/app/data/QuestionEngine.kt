package com.truthordare.app.data

import kotlin.random.Random

object QuestionEngine {
    private const val COMBOS_PER_TEMPLATE = 40
    private const val MAX_SLOT_ATTEMPTS = 200

    val all: List<Question> by lazy {
        val seeds = SeedBank.all()
        val expanded = Templates.all().flatMap { expand(it) }
        dedup(seeds + expanded)
    }

    private fun expand(tpl: QuestionTemplate): List<Question> {
        val rng = Random(tpl.id.hashCode())
        val seen = HashSet<String>()
        val out = ArrayList<Question>(tpl.combos)
        var attempts = 0
        while (out.size < tpl.combos && attempts < MAX_SLOT_ATTEMPTS) {
            attempts++
            val filled = fillPattern(tpl.pattern, tpl.slots, rng) ?: continue
            if (!seen.add(filled)) continue
            out += Question(
                id = stableId(tpl.id, filled),
                text = filled,
                type = tpl.type,
                category = tpl.category,
                intensity = tpl.intensity,
                rating = tpl.rating,
                requiresPartner = tpl.requiresPartner,
                timerSuggestedSeconds = tpl.timerSuggestedSeconds
            )
        }
        return out
    }

    private fun fillPattern(pattern: String, slots: List<String>, rng: Random): String? {
        var result = pattern
        for (slotName in slots) {
            val options = Templates.slots[slotName] ?: return null
            if (options.isEmpty()) return null
            val pick = options[rng.nextInt(options.size)]
            result = result.replace("{$slotName}", pick)
        }
        return if (result.contains("{")) null else result
    }

    private fun stableId(templateId: String, text: String): Int = (templateId + "|" + text).hashCode()

    private fun dedup(list: List<Question>): List<Question> {
        val seen = HashSet<String>()
        val out = ArrayList<Question>()
        for (question in list) {
            val key = normalize(question.text)
            if (seen.add(key)) {
                out += question
            }
        }
        return out
    }

    private fun normalize(value: String): String =
        value.lowercase().replace(Regex("[^a-z0-9 ]"), "").replace(Regex("\\s+"), " ").trim()

    fun forCategory(category: Category): List<Question> = all.filter { it.category == category }
    fun safePool(): List<Question> = all.filter { it.rating == ContentRating.SAFE }
    fun totalCount(): Int = all.size
}
