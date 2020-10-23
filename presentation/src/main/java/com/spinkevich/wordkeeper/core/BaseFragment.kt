package com.spinkevich.wordkeeper.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    open fun getLayoutRes(): Int {
        return -1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (getLayoutRes() != -1) {
            inflater.inflate(getLayoutRes(), container, false)
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }
}