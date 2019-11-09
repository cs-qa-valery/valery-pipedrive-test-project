package com.spinkevich.wordkeeper.feature.study

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.spinkevich.wordkeeper.BaseApp
import com.spinkevich.wordkeeper.R
import com.spinkevich.wordkeeper.core.BaseFragment
import com.spinkevich.wordkeeper.core.StudyViewModelFactory
import kotlinx.android.synthetic.main.fragment_study.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class StudyFragment : BaseFragment(), KodeinAware {

    override lateinit var kodein: Kodein

    private val viewModelFactory: StudyViewModelFactory by instance()
    private lateinit var viewModel: StudyViewModel
    private val adapter = StudyAdapter()

    override fun getLayoutRes() = R.layout.fragment_study

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodein = (requireActivity().application as BaseApp).kodein
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()

        viewModel.observeWords()
    }

    private fun initializeViews() {
        study_words_recycler.layoutManager = LinearLayoutManager(requireContext())
        study_words_recycler.adapter = adapter

        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(StudyViewModel::class.java)

        viewModel.wordsForStudying.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}