package xyz.kewiany.showcase.list

import kotlinx.coroutines.withContext
import xyz.kewiany.showcase.api.RepositoryApi
import xyz.kewiany.showcase.list.GetRepositoriesError.NoInternet
import xyz.kewiany.showcase.list.GetRepositoriesError.Unknown
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Error
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Success
import xyz.kewiany.showcase.utils.DispatcherProvider
import java.net.UnknownHostException

interface GetRepositories {
    suspend operator fun invoke(): GetRepositoriesResponse
}

class GetRepositoriesImpl(
    private val repositoryApi: RepositoryApi,
    private val dispatchers: DispatcherProvider
) : GetRepositories {

    override suspend fun invoke(): GetRepositoriesResponse = withContext(dispatchers.io()) {
        try {
            val response = repositoryApi.getRepositories("test")
            val data = response?.repositories
            Success(requireNotNull(data))
        } catch (e: Exception) {
            val error = when (e) {
                is UnknownHostException -> NoInternet
                else -> Unknown
            }
            Error(error)
        }
    }
}

sealed class GetRepositoriesResponse {
    data class Success(val repositories: List<Repository>) : GetRepositoriesResponse()
    data class Error(val error: GetRepositoriesError) : GetRepositoriesResponse()
}

sealed class GetRepositoriesError {
    object NoInternet : GetRepositoriesError()
    object Unknown : GetRepositoriesError()
}