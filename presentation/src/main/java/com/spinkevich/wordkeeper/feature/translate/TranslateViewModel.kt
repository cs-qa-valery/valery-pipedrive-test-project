package com.spinkevich.wordkeeper.feature.translate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spinkevich.domain.model.TranslationModel
import com.spinkevich.domain.usecase.TranslateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translateUseCase: TranslateUseCase
) : ViewModel() {

    val translationsList: MutableLiveData<TranslationModel> = MutableLiveData()

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun observeTranslation(direction: String, text: String) {
        uiScope.launch {
            var translations: TranslationModel? = null
            withContext(Dispatchers.IO) {
                translations = translateUseCase.translate(direction, text)
            }
            translationsList.value = translations
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}