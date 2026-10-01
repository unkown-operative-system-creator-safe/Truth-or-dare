package com.truthordare.app.data

class SessionQueue(
    pool: List<Question>,
    private val allowRepeatOnExhaustion: Boolean = true
) {
    private val usedIds = HashSet<Int>()
    private var pending = pool.shuffled()

    fun next(): Question? {
        if (pending.isEmpty()) {
            if (!allowRepeatOnExhaustion) return null
            usedIds.clear()
            pending = QuestionEngine.all.shuffled()
        }

        val item = pending.firstOrNull { it.id !in usedIds } ?: return null
        usedIds += item.id
        pending = pending.filterNot { it.id == item.id }
        return item
    }

    fun reset(pool: List<Question>) {
        usedIds.clear()
        pending = pool.shuffled()
    }

    fun sizeRemaining(): Int = pending.size
}
