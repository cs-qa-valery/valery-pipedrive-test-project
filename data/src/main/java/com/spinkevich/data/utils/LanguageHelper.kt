package com.spinkevich.data.utils

object LanguageHelper {

    val map: LinkedHashMap<String, String> by lazy {
        LinkedHashMap<String, String>(92).apply {
            put("Azerbaijan", "az")
            put("Afrikaans", "af")
            put("Albanian", "sq")
            put("Armenian", "hy")
            put("Belarusian", "be")
            put("Bulgarian", "bg")
            put("Catalan", "ca")
            put("Chinese", "zh")
            put("Croatian", "hr")
            put("Czech", "cs")
            put("Danish", "da")
            put("Dutch", "nl")
            put("English", "en")
            put("Estonian", "et")
            put("Finnish", "fi")
            put("French", "fr")
            put("German", "de")
            put("Greek", "el")
            put("Hungarian", "hu")
            put("Italian", "it")
            put("Khmer", "km")
            put("Latvian", "lv")
            put("Lithuanian", "lt")
            put("Macedonian", "mk")
            put("Norwegian", "no")
            put("Polish", "pl")
            put("Portuguese", "pt")
            put("Romanian", "ro")
            put("Russian", "ru")
            put("Serbian", "sr")
            put("Slovakian", "sk")
            put("Slovenian", "sl")
            put("Spanish", "es")
            put("Swedish", "sv")
            put("Tagalog", "tl")
            put("Turkish", "tr")
            put("Ukrainian", "uk")
        }
    }

    fun getLanguages() = map.keys.toList()
}