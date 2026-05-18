package com.bunyamin027.zikrify.model

data class DailyRecord(
    val date: String,
    val totalCount: Int
)

data class Badge(
    val id: String,
    val nameTR: String,
    val nameEN: String,
    val icon: String
) {
    companion object {
        val allBadges = listOf(
            Badge("first_step", "İlk Adım", "First Step", "star.fill"),
            Badge("dedicated", "Kararlı", "Dedicated", "flame.fill"),
            Badge("master", "Usta", "Master", "rosette")
        )
    }
}

object Moods {
    const val morning = "morning"
    const val night = "night"
    const val consolation = "consolation"
    const val secure = "secure"
    const val gratitude = "gratitude"
    const val ask = "ask"
    const val purification = "purification"
    const val joy = "joy"

    fun title(id: String, isEn: Boolean): String {
        return when (id) {
            morning -> if (isEn) "Morning" else "Sabah"
            night -> if (isEn) "Night" else "Akşam"
            consolation -> if (isEn) "Consolation" else "Teselli"
            secure -> if (isEn) "Secure" else "Güvende"
            gratitude -> if (isEn) "Gratitude" else "Şükür"
            ask -> if (isEn) "Ask" else "İstek"
            purification -> if (isEn) "Purification" else "Arınma"
            joy -> if (isEn) "Joy" else "Neşe"
            else -> id
        }
    }
}
