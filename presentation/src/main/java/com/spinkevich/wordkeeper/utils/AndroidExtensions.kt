package com.spinkevich.wordkeeper.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addFragmentToStack(fragment: Fragment, @IdRes container: Int) {
    supportFragmentManager.beginTransaction()
        .add(container, fragment)
        .addToBackStack(null)
        .commit()
}

fun <T : View> View.bindView(id: Int, action: (() -> Unit)? = null): Lazy<T> {
    return lazy {
        val view = findViewById<T>(id)
        action?.invoke()
        view
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}