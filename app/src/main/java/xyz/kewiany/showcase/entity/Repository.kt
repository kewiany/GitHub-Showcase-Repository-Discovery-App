package xyz.kewiany.showcase.entity

import android.content.Context
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.threeten.bp.Instant
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.utils.dateFormatter
import xyz.kewiany.showcase.utils.todayDateFormatter
import xyz.kewiany.showcase.utils.zoneID


@Serializable
data class Repository(
    val id: Long,
    val name: String,
    val description: String = "",
    val language: String = "",
    @SerialName("stargazers_count") val stars: Int,
    @SerialName("watchers_count") val watchers: Int,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("owner") val user: User
) {
    val updatedAtInstant by lazy { Instant.parse(updatedAt) }
    fun isUpdatedToday(): Boolean {
        val updatedAtLocalDate = updatedAtInstant.atZone(zoneID).toLocalDate()
        val todayLocalDate = Instant.now().atZone(zoneID).toLocalDate()
        return updatedAtLocalDate.isEqual(todayLocalDate)
    }
}

fun Repository.toFormattedDate(context: Context): String? {
    val localDateTime = updatedAtInstant.atZone(zoneID).toLocalDateTime()
    return if (isUpdatedToday()) {
        context.getString(R.string.updated, localDateTime.format(todayDateFormatter).format(todayDateFormatter))
    } else {
        context.getString(R.string.updated_on, localDateTime.format(dateFormatter).format(dateFormatter))
    }
}