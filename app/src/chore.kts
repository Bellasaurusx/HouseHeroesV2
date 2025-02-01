data class Chore(
    val name: String,
    var completed: Boolean,
    var locked: Boolean
) {
    fun isActionable(): Boolean {
        return !locked && !completed
    }
}