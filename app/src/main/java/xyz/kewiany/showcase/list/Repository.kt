package xyz.kewiany.showcase.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val id: Long,
    val name: String,
    val description: String,
    @SerialName("owner") val user: User
)