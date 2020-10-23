package com.spinkevich.wordkeeper.utils

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.*

class EditTextDebounceListener(
    private val listener: (String) -> Unit,
    private val debounce: Long = 300
) : TextWatcher {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var previousText = ""

    override fun afterTextChanged(text: Editable) {
        val searchText = text.toString().trim()
        if (searchText == previousText)
            return

        previousText = searchText

        uiScope.launch {
            delay(debounce)
            if (searchText != previousText)
                return@launch

            listener.invoke(previousText)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
}