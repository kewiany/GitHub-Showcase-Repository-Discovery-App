package xyz.kewiany.showcase.utils

import org.threeten.bp.format.DateTimeFormatter

val dateFormatter: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("d MMM") }