package com.spinkevich.wordkeeper.feature.translate

import android.content.Context
import android.content.res.ColorStateList
import com.google.android.material.chip.Chip
import com.spinkevich.wordkeeper.R

class TranslationChip(context: Context?) : Chip(context) {

    fun init(word: String, color: Int = R.color.colorPrimary) {
        text = word
        isCloseIconEnabled = true
        setTextColor(resources.getColor(R.color.white))
        closeIconTint = ColorStateList.valueOf(resources.getColor(R.color.white))
        closeIcon = resources.getDrawable(R.drawable.ic_add_white_24dp)
        setChipBackgroundColorResource(color)
    }
}