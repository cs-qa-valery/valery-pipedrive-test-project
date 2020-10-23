package com.spinkevich.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WordModel(
    val original: String,
    val translation: String,
    val direction: String
) : Parcelable {

    open fun areItemsTheSame(newItem: WordModel) = this == newItem

    open fun areContentsTheSame(newItem: WordModel) = this == newItem
}