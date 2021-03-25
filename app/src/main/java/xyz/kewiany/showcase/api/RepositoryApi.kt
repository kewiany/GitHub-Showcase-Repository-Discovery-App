package xyz.kewiany.showcase.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.kewiany.showcase.list.Repository

interface RepositoryApi {
    suspend fun getRepositories(query: String): RepositoriesResponse?
}

@Serializable
data class RepositoriesResponse(
    @SerialName("items") val repositories: List<Repository>
)
