package xyz.kewiany.showcase.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snackBar = Snackbar.make(this, message, length)
    snackBar.show()
}

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}