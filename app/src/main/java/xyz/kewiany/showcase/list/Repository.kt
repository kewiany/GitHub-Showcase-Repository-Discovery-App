package xyz.kewiany.showcase.list

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val name: String,
    val description: String
)