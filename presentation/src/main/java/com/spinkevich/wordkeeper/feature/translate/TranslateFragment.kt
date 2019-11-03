package com.spinkevich.wordkeeper.feature.translate

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.spinkevich.domain.model.Translation
import com.spinkevich.wordkeeper.BaseApp
import com.spinkevich.wordkeeper.R
import com.spinkevich.wordkeeper.core.BaseFragment
import com.spinkevich.wordkeeper.core.TranslateViewModelFactory
import com.spinkevich.wordkeeper.utils.afterTextChanged
import kotlinx.android.synthetic.main.fragment_translate.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class TranslateFragment : BaseFragment(), KodeinAware {

    override lateinit var kodein: Kodein

    private val viewModelFactory: TranslateViewModelFactory by instance()
    private lateinit var viewModel: TranslateViewModel

    override fun getLayoutRes() = R.layout.fragment_translate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodein = (requireActivity().application as BaseApp).kodein
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews()
        text_for_translation.afterTextChanged {
            viewModel.observeTranslation("en-ru", it)
        }
        viewModel.translationsList.observe(this, Observer {
            translation_group.removeAllViews()
            it.translations.forEach { createChip(it) }
        })
    }

    private fun initializeViews() {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(TranslateViewModel::class.java)
    }

    private fun createChip(translation: Translation) {
        val chip = TranslationChip(context)
        chip.init(translation.value)
        translation_group.addView(chip)
    }
}