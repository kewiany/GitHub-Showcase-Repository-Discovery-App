package xyz.kewiany.showcase.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Repository(
    val id: Long,
    val name: String,
    val description: String = "",
    val language: String = "",
    @SerialName("stargazers_count") val stars: Int,
    @SerialName("watchers_count") val watchers: Int,
    @SerialName("forks_count") val forks: Int,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("owner") val user: User
)