package xyz.kewiany.showcase.utils

import android.content.Context
import xyz.kewiany.showcase.R

enum class ContentType { START, EMPTY; }

fun ContentType.translate(context: Context): String = context.getString(
    when (this) {
        ContentType.START -> R.string.start
        ContentType.EMPTY -> R.string.empty
    }
)