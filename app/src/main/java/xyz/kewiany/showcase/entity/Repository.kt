package xyz.kewiany.showcase.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import xyz.kewiany.showcase.utils.dateFormatter


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
)

fun Repository.toFormattedDate(): String? {
    val instant = Instant.parse(updatedAt)
    val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime().format(dateFormatter)
    return localDateTime.format(dateFormatter)
}