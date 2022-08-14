package com.dnd.niceteam.ui.onboarding.nickname

enum class Level(val level: String) {
    LEVEL1("Lv.1"),
    LEVEL2("Lv.2"),
    LEVEL3("Lv.3"),
    LEVEL4("Lv.4");

    companion object {
        fun valueName(): List<String> = values().map { it.level }
    }
}