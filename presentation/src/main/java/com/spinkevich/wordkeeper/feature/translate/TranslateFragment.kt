package com.spinkevich.wordkeeper.feature.translate

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.spinkevich.data.utils.LanguageHelper
import com.spinkevich.domain.model.Translation
import com.spinkevich.wordkeeper.R
import com.spinkevich.wordkeeper.core.BaseFragment
import com.spinkevich.wordkeeper.utils.EditTextDebounceListener
import com.spinkevich.wordkeeper.utils.onAnimationEnd
import com.spinkevich.wordkeeper.utils.viewModel
import kotlinx.android.synthetic.main.fragment_translate.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

private const val FROM_LANGUAGE_CODE = 21
private const val TO_LANGUAGE_CODE = 22

class TranslateFragment : BaseFragment(), KodeinAware, OnLanguageSelectedListener {

    override val kodein: Kodein by closestKodein()

    private val viewModel: TranslateViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_translate

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
            Snackbar.make(
                view,
                resources.getString(R.string.translate_screen_error_translate),
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.clearChips()
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
        val chooseLanguageDialog = ChooseLanguageDialog()
        from_language.setOnClickListener {
            chooseLanguageDialog.setTargetFragment(this, FROM_LANGUAGE_CODE)
            chooseLanguageDialog.show(fragmentManager, ChooseLanguageDialog.TAG)
        }
        to_language.setOnClickListener {
            chooseLanguageDialog.setTargetFragment(this, TO_LANGUAGE_CODE)
            chooseLanguageDialog.show(fragmentManager, ChooseLanguageDialog.TAG)
        }
        text_for_translation.addTextChangedListener(
            EditTextDebounceListener(
                listener = { viewModel.observeTranslation(it) }
            )
        )
    }

    private fun createChip(translation: Translation) {
        val chip = TranslationChip(context)
        chip.init(translation.value)
        chip.setOnClickListener {
            val originalWord = text_for_translation.text.toString()
            viewModel.observeClickTranslation(originalWord, translation.value)

            animateFadeOut(it)
        }
        translation_group.addView(chip)
    }

    private fun animateFadeOut(view: View) {
        val startAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_out_animation)
        view.startAnimation(startAnimation)
        startAnimation.onAnimationEnd { view.visibility = View.INVISIBLE }
    }
}