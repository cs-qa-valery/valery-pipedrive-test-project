package com.spinkevich.wordkeeper.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spinkevich.domain.usecase.TranslateUseCase
import com.spinkevich.wordkeeper.feature.translate.TranslateViewModel

class TranslateViewModelFactory(
    private val translateUseCase: TranslateUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TranslateViewModel(translateUseCase) as T
    }
}