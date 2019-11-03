package com.spinkevich.wordkeeper.feature.translate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.spinkevich.data.utils.LanguageHelper
import com.spinkevich.wordkeeper.R
import kotlinx.android.synthetic.main.dialog_choose_language.*

class ChooseLanguageDialog : DialogFragment() {

    private lateinit var onLanguagesSelected: OnLanguageSelectedListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.dialog_choose_language, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, LanguageHelper.getLanguages())
        languages_list.adapter = adapter

        languages_list.setOnItemClickListener { _, _, position, _ ->
            onLanguagesSelected.selectLanguage(LanguageHelper.getLanguages()[position], targetRequestCode)
            targetFragment!!.onActivityResult(targetRequestCode, targetRequestCode, activity?.intent)
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onLanguagesSelected = targetFragment as OnLanguageSelectedListener
        } catch (ex: ClassCastException) {
            throw ClassCastException("$context must implement OnLanguagesSelected interface")
        }
    }

    companion object {
        const val TAG = "ChooseLanguageDialog"
    }
}