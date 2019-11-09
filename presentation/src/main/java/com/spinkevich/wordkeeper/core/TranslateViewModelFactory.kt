package com.spinkevich.wordkeeper.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spinkevich.domain.usecase.StudyWordsUseCase
import com.spinkevich.domain.usecase.TranslateUseCase
import com.spinkevich.wordkeeper.feature.translate.TranslateViewModel

class TranslateViewModelFactory(
    private val translateUseCase: TranslateUseCase,
    private val studyWordsUseCase: StudyWordsUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TranslateViewModel(translateUseCase, studyWordsUseCase) as T
    }
}