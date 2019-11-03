package com.spinkevich.wordkeeper.feature.translate

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.spinkevich.data.utils.LanguageHelper
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

private const val FROM_LANGUAGE_CODE = 21
private const val TO_LANGUAGE_CODE = 22

class TranslateFragment : BaseFragment(), KodeinAware, OnLanguageSelectedListener {

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

        viewModel.translationsList.observe(this, Observer {
            translation_group.removeAllViews()
            it.translations.forEach { createChip(it) }
        })
        viewModel.fromLanguage.observe(this, Observer {
            from_language.text = it.toUpperCase()
        })
        viewModel.toLanguage.observe(this, Observer {
            to_language.text = it.toUpperCase()
        })
        viewModel.errors.observe(this, Observer {
            Snackbar.make(view, resources.getString(R.string.translate_screen_error_translate), Snackbar.LENGTH_SHORT)
                .show()
        })
    }

    override fun selectLanguage(language: String, code: Int) {
        when (code) {
            FROM_LANGUAGE_CODE -> {
                translation_group.removeAllViews()
                viewModel.fromLanguage.value = LanguageHelper.map[language]?.toLowerCase()
            }
            TO_LANGUAGE_CODE -> {
                translation_group.removeAllViews()
                viewModel.toLanguage.value = LanguageHelper.map[language]?.toLowerCase()
            }
        }
        if (text_for_translation.editableText.isNotEmpty()) {
            viewModel.observeTranslation(text_for_translation.text.toString())
        }
    }

    private fun initializeViews() {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(TranslateViewModel::class.java)

        val chooseLanguageDialog = ChooseLanguageDialog()
        from_language.setOnClickListener {
            chooseLanguageDialog.setTargetFragment(this, FROM_LANGUAGE_CODE)
            chooseLanguageDialog.show(fragmentManager, ChooseLanguageDialog.TAG)
        }
        to_language.setOnClickListener {
            chooseLanguageDialog.setTargetFragment(this, TO_LANGUAGE_CODE)
            chooseLanguageDialog.show(fragmentManager, ChooseLanguageDialog.TAG)
        }
        text_for_translation.afterTextChanged {
            viewModel.observeTranslation(it)
        }
    }

    private fun createChip(translation: Translation) {
        val chip = TranslationChip(context)
        chip.init(translation.value)
        translation_group.addView(chip)
    }
}