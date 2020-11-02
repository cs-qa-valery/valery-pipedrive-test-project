package com.spinkevich.wordkeeper.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spinkevich.wordkeeper.BaseApp
import com.spinkevich.wordkeeper.R
import com.spinkevich.wordkeeper.feature.study.StudyFragment
import com.spinkevich.wordkeeper.feature.translate.TranslateFragment
import com.spinkevich.wordkeeper.utils.addFragmentToStack
import com.spinkevich.wordkeeper.utils.replaceFragmentToStack
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as BaseApp).addModule()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentToStack(TranslateFragment(), R.id.fragment_container)

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_translate -> replaceFragmentToStack(TranslateFragment(), R.id.fragment_container)
                R.id.action_study_list -> replaceFragmentToStack(StudyFragment(), R.id.fragment_container)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
