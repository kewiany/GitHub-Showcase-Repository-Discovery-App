package xyz.kewiany.showcase.utils

import org.threeten.bp.format.DateTimeFormatter

val todayDateFormatter: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("H") }
val dateFormatter: DateTimeFormatter by lazy { DateTimeFormatter.ofPattern("d MMM") }