package com.spinkevich.wordkeeper.feature.study

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spinkevich.domain.model.WordModel
import com.spinkevich.domain.usecase.StudyWordsUseCase
import kotlinx.coroutines.*

class StudyViewModel(
    private val studyWordsUseCase: StudyWordsUseCase
) : ViewModel() {

    var wordsForStudying = MutableLiveData<List<WordModel>>()
    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun observeWords() {
        uiScope.launch {
            var words: List<WordModel>? = null
            withContext(Dispatchers.IO) {
                words = studyWordsUseCase.getWords()
            }
            wordsForStudying.value = words
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}