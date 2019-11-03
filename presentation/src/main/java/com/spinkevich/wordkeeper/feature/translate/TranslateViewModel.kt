package com.spinkevich.wordkeeper.feature.translate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spinkevich.domain.model.TranslationModel
import com.spinkevich.domain.usecase.TranslateUseCase
import kotlinx.coroutines.*

class TranslateViewModel(
    private val translateUseCase: TranslateUseCase
) : ViewModel() {

    val translationsList: MutableLiveData<TranslationModel> = MutableLiveData()
    val errors: MutableLiveData<Exception> = MutableLiveData()

    var fromLanguage: MutableLiveData<String> = MutableLiveData("en")
    var toLanguage: MutableLiveData<String> = MutableLiveData("ru")

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun observeTranslation(text: String) {
        val direction = "${fromLanguage.value}-${toLanguage.value}"
        uiScope.launch {
            try {
                var translations: TranslationModel? = null
                withContext(Dispatchers.IO) {
                    translations = translateUseCase.translate(direction, text)
                }
                translationsList.value = translations
            } catch (ex: Exception) {
                errors.value = ex
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}