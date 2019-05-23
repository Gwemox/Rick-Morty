package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.view.fragment.DetailFragment
import com.ynov.kotlin.rickmorty.presentation.list.view.fragment.CharacterListFragment
import com.ynov.kotlin.rickmorty.presentation.list.view.fragment.EpisodeListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterListFragment = CharacterListFragment()
        val episodesFragment = EpisodeListFragment()

        main_activity_bottom_navigation.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.bottom_menu_action_characters_list -> characterListFragment
                R.id.bottom_menu_action_episodes_list -> episodesFragment
                else -> characterListFragment
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_fragment_container, fragment)
                .commit()
            return@setOnNavigationItemSelectedListener true
        }
        main_activity_bottom_navigation.selectedItemId = R.id.bottom_menu_action_characters_list
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        AlertDialog.Builder(this)
            .setTitle("Quitter")
            .setMessage("Voulez-vous fermer l'application ?")
            .setPositiveButton("Oui") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .setNegativeButton("Non") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
