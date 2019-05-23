package com.ynov.kotlin.rickmorty.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String, time: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, time).show()
}
