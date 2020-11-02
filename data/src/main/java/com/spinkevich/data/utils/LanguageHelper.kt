package com.spinkevich.data.utils

object LanguageHelper {

    val map: LinkedHashMap<String, String> by lazy {
        LinkedHashMap<String, String>(92).apply {
            put("English", "en")
            put("German", "de")
            put("Russian", "ru")
            put("Estonian", "fin")
            put("Turkish", "tr")
        }
    }

    fun getLanguages() = map.keys.toList()
}