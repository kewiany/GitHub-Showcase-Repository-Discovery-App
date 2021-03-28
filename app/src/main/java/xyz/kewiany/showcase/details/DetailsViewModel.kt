package xyz.kewiany.showcase.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import xyz.kewiany.showcase.details.GetRepositoryDetailsError.NoInternet
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Error
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.entity.Repository
import xyz.kewiany.showcase.entity.User
import xyz.kewiany.showcase.utils.DateTimeFormatter
import xyz.kewiany.showcase.utils.DispatcherProvider
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.NavigationCommander

class DetailsViewModel(
    private val state: DetailsState,
    private val getRepositoryDetails: GetRepositoryDetails,
    private val getUser: GetUser,
    private val navigationCommander: NavigationCommander,
    private val dateTimeFormatter: DateTimeFormatter,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private var _repository: Repository? = null
    private var _owner: User? = null
    private var _followers: List<User>? = null

    val isLoading: Flow<Boolean> = state.commonState.isLoading
    val name: Flow<String> = state.name
    val description: Flow<String> = state.description
    val stars: Flow<String> = state.stars
    val watchers: Flow<String> = state.watchers
    val forks: Flow<String> = state.forks
    val updatedAt: Flow<String> = state.updatedAt
    val createdAt: Flow<String> = state.createdAt
    val language: Flow<String> = state.language
    val owner: Flow<String> = state.owner
    val avatar: Flow<String> = state.avatar
    val followers: Flow<List<User>> = state.followers
    val error: Flow<ErrorType?> = state.error

    init {
        state.commonState.isLoading.value = false
        updateState()
        state.error.value = null
    }

    fun back() {
        navigationCommander.popBackStack()
    }

    fun load(id: Long) = viewModelScope.launch(dispatchers.main()) {
        state.error.value = null
        state.commonState.isLoading.value = true
        try {
            loadRepositoryDetails(id)
            loadUser(requireNotNull(_repository?.user?.name))
            updateState()
        } catch (e: Exception) {
            when (e) {
                is LoadDetailsException -> {
                    state.error.value = e.errorType
                }
            }
        }
        state.commonState.isLoading.value = false
    }

    private fun updateState() = with(state) {
        name.value = _repository?.name ?: ""
        description.value = _repository?.description ?: ""
        stars.value = _repository?.stars?.toString() ?: ""
        watchers.value = _repository?.watchers?.toString() ?: ""
        forks.value = _repository?.forks?.toString() ?: ""
        updatedAt.value = format(_repository?.updatedAt)
        createdAt.value = format(_repository?.createdAt)
        language.value = _repository?.language ?: ""
        owner.value = _owner?.name ?: ""
        avatar.value = _owner?.avatar ?: ""
        followers.value = _followers ?: emptyList()
    }

    private suspend fun loadRepositoryDetails(id: Long) {
        when (val response = getRepositoryDetails(id)) {
            is Success -> {
                _repository = response.repository
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
                _owner = response.user
                _followers = response.followers
            }
            is GetUserResponse.Error -> {
                val error = if (response.error is GetUserError.NoInternet) ErrorType.NO_INTERNET else ErrorType.UNKNOWN
                throw LoadDetailsException(error)
            }
        }
    }

    private fun format(date: String?) = if (date != null && date.isNotEmpty()) dateTimeFormatter.format(date) else ""
}

class LoadDetailsException(val errorType: ErrorType) : Exception()