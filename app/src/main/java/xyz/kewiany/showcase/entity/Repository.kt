package xyz.kewiany.showcase.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.threeten.bp.Instant


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
    val updatedAtInstant: Instant by lazy { Instant.parse(updatedAt) }
}