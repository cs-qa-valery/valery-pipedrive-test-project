package com.spinkevich.wordkeeper.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spinkevich.wordkeeper.R
import com.spinkevich.wordkeeper.feature.translate.TranslateFragment
import com.spinkevich.wordkeeper.utils.addFragmentToStack
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentToStack(TranslateFragment(), R.id.fragment_container)
    }
}
