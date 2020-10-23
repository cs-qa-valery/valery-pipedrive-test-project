package com.spinkevich.wordkeeper.feature.study

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.spinkevich.wordkeeper.R
import com.spinkevich.wordkeeper.core.BaseFragment
import com.spinkevich.wordkeeper.utils.viewModel
import kotlinx.android.synthetic.main.fragment_study.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

class StudyFragment : BaseFragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModel: StudyViewModel by viewModel()
    private val adapter = StudyAdapter()

    override fun getLayoutRes() = R.layout.fragment_study

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()

        viewModel.observeWords()
    }

    private fun initializeViews() {
        study_words_recycler.layoutManager = LinearLayoutManager(requireContext())
        study_words_recycler.adapter = adapter

        viewModel.wordsForStudying.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}