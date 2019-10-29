package com.spinkevich.wordkeeper.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addFragmentToStack(fragment: Fragment, @IdRes container: Int) {
    supportFragmentManager.beginTransaction()
        .add(container, fragment)
        .addToBackStack(null)
        .commit()
}