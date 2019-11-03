package com.spinkevich.domain.model

data class TranslationModel(val translations: List<Translation>)

data class Translation(val value: String)