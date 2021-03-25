package xyz.kewiany.showcase.utils

import android.content.Context
import xyz.kewiany.showcase.R

enum class ErrorType {
    NO_INTERNET, UNKNOWN;
}

fun ErrorType.translate(context: Context): String = context.getString(
    when (this) {
        ErrorType.NO_INTERNET -> R.string.no_internet
        ErrorType.UNKNOWN -> R.string.unknown
    }
)