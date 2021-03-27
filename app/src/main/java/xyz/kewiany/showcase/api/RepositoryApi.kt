package xyz.kewiany.showcase.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.kewiany.showcase.entity.Repository

interface RepositoryApi {
    suspend fun getRepositories(query: String): RepositoriesResponse?
    suspend fun getRepository(id: Long): Repository?
}

@Serializable
data class RepositoriesResponse(
    @SerialName("items") val repositories: List<Repository>
)
