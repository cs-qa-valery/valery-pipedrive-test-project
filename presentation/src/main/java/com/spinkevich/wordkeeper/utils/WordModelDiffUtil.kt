package com.spinkevich.wordkeeper.utils

import androidx.recyclerview.widget.DiffUtil
import com.spinkevich.domain.model.WordModel

object WordModelDiffUtil : DiffUtil.ItemCallback<WordModel>() {

    override fun areItemsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}