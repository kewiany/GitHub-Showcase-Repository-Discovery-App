package xyz.kewiany.showcase.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val avatar: String,
    val followers: Int? = null
)