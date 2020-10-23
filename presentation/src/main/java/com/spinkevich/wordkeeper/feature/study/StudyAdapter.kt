package com.spinkevich.wordkeeper.feature.study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.spinkevich.domain.model.WordModel
import com.spinkevich.wordkeeper.R
import com.spinkevich.wordkeeper.utils.WordModelDiffUtil
import kotlinx.android.synthetic.main.study_item.view.*

class StudyAdapter : ListAdapter<WordModel, StudyAdapter.StudyViewHolder>(WordModelDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
        return StudyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.study_item, parent, false))
    }

    override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StudyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(wordModel: WordModel) {
            itemView.original_text.text = wordModel.original
            itemView.translation_text.text = wordModel.translation
        }
    }
}