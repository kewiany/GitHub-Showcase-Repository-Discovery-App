package xyz.kewiany.showcase.details

import kotlinx.coroutines.withContext
import xyz.kewiany.showcase.details.GetRepositoryDetailsError.NoInternet
import xyz.kewiany.showcase.details.GetRepositoryDetailsError.Unknown
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Error
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.list.Repository
import xyz.kewiany.showcase.utils.DispatcherProvider
import java.net.UnknownHostException

interface GetRepositoryDetails {
    suspend operator fun invoke(id: Long): GetRepositoryDetailsResponse
}

class GetRepositoryDetailsImpl(
    private val dispatchers: DispatcherProvider
) : GetRepositoryDetails {

    override suspend fun invoke(id: Long): GetRepositoryDetailsResponse = withContext(dispatchers.io()) {
        try {
            Success(Repository(0, "name", "description"))
        } catch (e: Exception) {
            val error = when (e) {
                is UnknownHostException -> NoInternet
                else -> Unknown
            }
            Error(error)
        }
    }
}


sealed class GetRepositoryDetailsResponse {
    data class Success(val repository: Repository) : GetRepositoryDetailsResponse()
    data class Error(val error: GetRepositoryDetailsError) : GetRepositoryDetailsResponse()
}

sealed class GetRepositoryDetailsError {
    object NoInternet : GetRepositoryDetailsError()
    object Unknown : GetRepositoryDetailsError()
}