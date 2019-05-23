package com.ynov.kotlin.rickmorty.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.view.fragment.DetailFragment


class DetailActivity : AppCompatActivity() {

    companion object {
        private const val CHARACTER_ID = "characterId"
        fun newIntent(context: Context, characterId: Int): Intent =
            Intent(context, DetailActivity::class.java).putExtra(CHARACTER_ID, characterId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_detail)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.detail_activity_fragment_container,
                DetailFragment(intent.getIntExtra(CHARACTER_ID, -1))
            )
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
