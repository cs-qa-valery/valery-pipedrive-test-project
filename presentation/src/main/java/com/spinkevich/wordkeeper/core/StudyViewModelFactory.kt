package com.spinkevich.wordkeeper.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spinkevich.domain.usecase.StudyWordsUseCase
import com.spinkevich.wordkeeper.feature.study.StudyViewModel

class StudyViewModelFactory(
    private val studyWordsUseCase: StudyWordsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudyViewModel(studyWordsUseCase) as T
    }
}