package divyansh.tech.healthappudemy.home.ListScreen.model

enum class CarbType {
    VERY_LOW, LOW, MED, HIGH, VERY_HIGH
}

data class ListItem(
    val id: Int,
    val painter: Int,
    val carbType: CarbType = CarbType.LOW,
    val text: String = "",
    val cals: String = ""
)