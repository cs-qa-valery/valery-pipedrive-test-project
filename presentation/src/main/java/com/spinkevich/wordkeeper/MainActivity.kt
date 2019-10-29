package com.spinkevich.wordkeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spinkevich.wordkeeper.feature.translate.TranslateFragment
import com.spinkevich.wordkeeper.utils.addFragmentToStack

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragmentToStack(TranslateFragment(), R.id.fragment_container)
    }
}
