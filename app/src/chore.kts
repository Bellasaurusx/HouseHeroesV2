data class Chore(
    val name: String,
    var locked: Boolean,
    var completed: Boolean = false
) {
    fun isActionable(): Boolean = !locked && !completed
}