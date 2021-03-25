package xyz.kewiany.showcase.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snackBar = Snackbar.make(this, message, length)
    snackBar.show()
}
