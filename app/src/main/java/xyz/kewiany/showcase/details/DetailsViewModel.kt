package xyz.kewiany.showcase.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import xyz.kewiany.showcase.details.GetRepositoryDetailsError.NoInternet
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Error
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.entity.Repository
import xyz.kewiany.showcase.entity.User
import xyz.kewiany.showcase.utils.DispatcherProvider
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.NavigationCommander

class DetailsViewModel(
    private val state: DetailsState,
    private val getRepositoryDetails: GetRepositoryDetails,
    private val getUser: GetUser,
    private val navigationCommander: NavigationCommander,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    var repository: Repository? = null

    val isLoading: Flow<Boolean> = state.commonState.isLoading
    val name: Flow<String> = state.name
    val description: Flow<String> = state.description
    val userName: Flow<String> = state.userName
    val followers: Flow<List<User>> = state.followers
    val error: Flow<ErrorType?> = state.error

    init {
        state.commonState.isLoading.value = false
        state.name.value = ""
        state.description.value = ""
        state.userName.value = ""
        state.followers.value = emptyList()
        state.error.value = null
    }

    private suspend fun loadRepositoryDetails(id: Long) {
        when (val response = getRepositoryDetails(id)) {
            is Success -> {
                val item = response.repository
                repository = item
                state.name.value = item.name
                state.description.value = item.description
            }
            is Error -> {
                val error = if (response.error is NoInternet) ErrorType.NO_INTERNET else ErrorType.UNKNOWN
                throw LoadDetailsException(error)
            }
        }
    }

    private suspend fun loadUser(name: String) {
        when (val response = getUser(name)) {
            is GetUserResponse.Success -> {
                state.userName.value = response.user.name
                state.followers.value = response.followers
            }
            is GetUserResponse.Error -> {
                val error = if (response.error is GetUserError.NoInternet) ErrorType.NO_INTERNET else ErrorType.UNKNOWN
                throw LoadDetailsException(error)
            }
        }
    }

    fun load(id: Long) = viewModelScope.launch(dispatchers.main()) {
        state.error.value = null
        state.commonState.isLoading.value = true
        try {
            loadRepositoryDetails(id)
            loadUser(requireNotNull(repository?.user?.name))
        } catch (e: Exception) {
            when (e) {
                is CancellationException -> {
                    state.name.value = ""
                    state.description.value = ""
                }
                is LoadDetailsException -> {
                    state.error.value = e.errorType
                }
            }
        }
        state.commonState.isLoading.value = false
    }

    fun back() {
        navigationCommander.popBackStack()
    }
}

class LoadDetailsException(val errorType: ErrorType) : Exception()