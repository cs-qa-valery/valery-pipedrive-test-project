package com.spinkevich.data.source.api.response

import com.google.gson.annotations.SerializedName

data class TranslateResponse(
    @SerializedName("def")
    val def: List<Definition>?
)

data class Definition(
    @SerializedName("tr")
    val tr: List<Translation>?
)

data class Translation(
    @SerializedName("text")
    val text: String?,
    @SerializedName("syn")
    val syn: List<Translation>?
)