package xyz.kewiany.showcase.details

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import xyz.kewiany.showcase.api.UserApi
import xyz.kewiany.showcase.details.GetUserError.NoInternet
import xyz.kewiany.showcase.details.GetUserError.Unknown
import xyz.kewiany.showcase.details.GetUserResponse.Error
import xyz.kewiany.showcase.details.GetUserResponse.Success
import xyz.kewiany.showcase.list.User
import xyz.kewiany.showcase.utils.DispatcherProvider
import java.net.UnknownHostException

interface GetUser {
    suspend operator fun invoke(name: String): GetUserResponse
}

class GetUserImpl(
    private val api: UserApi,
    private val dispatchers: DispatcherProvider
) : GetUser {

    override suspend fun invoke(name: String): GetUserResponse = withContext(dispatchers.io()) {
        try {
            coroutineScope {
                val getUserCall = async { api.getUser(name) }
                val getFollowersCall = async { api.getFollowersForUser(name) }
                val user = getUserCall.await()
                val followers = getFollowersCall.await()
                Success(requireNotNull(user), requireNotNull(followers))
            }
        } catch (e: Exception) {
            val error = when (e) {
                is UnknownHostException -> NoInternet
                else -> Unknown
            }
            Error(error)
        }
    }
}


sealed class GetUserResponse {
    data class Success(val user: User, val followers: List<User>) : GetUserResponse()
    data class Error(val error: GetUserError) : GetUserResponse()
}

sealed class GetUserError {
    object NoInternet : GetUserError()
    object Unknown : GetUserError()
}