package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.view.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, ListFragment())
            .commit()
    }
}
