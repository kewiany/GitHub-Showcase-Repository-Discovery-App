package xyz.kewiany.showcase.utils

import android.content.Context
import org.threeten.bp.*
import xyz.kewiany.showcase.R

interface DateTimeFormatter {
    fun format(date: String): String
}

class DateTimerFormatterImpl(clock: Clock, private val context: Context) : DateTimeFormatter {

    private val now: Instant
        get() = Instant.now()
    private val zoneID: ZoneId = clock.zone
    private val nowZonedDateTime: ZonedDateTime = now.atZone(zoneID)

    override fun format(date: String): String {
        val zonedDateTime = Instant.parse(date).atZone(zoneID)
        val localDateTime = zonedDateTime.toLocalDateTime()
        val isToday = isToday(nowZonedDateTime.toLocalDate(), zonedDateTime.toLocalDate())
        val (resId, formatter) = when {
            isToday && isSameHour(nowZonedDateTime, zonedDateTime) -> R.string.minutes_ago to todayMinutesFormatter
            isToday -> R.string.hours_ago to todayHoursFormatter
            else -> R.string.on to dateFormatter
        }
        return context.getString(resId, localDateTime.format(formatter).format(formatter))
    }

    private fun isToday(now: LocalDate, dateToCheck: LocalDate): Boolean = now.isEqual(dateToCheck)
    private fun isSameHour(now: ZonedDateTime, dateToCheck: ZonedDateTime) = now.hour == dateToCheck.hour
}