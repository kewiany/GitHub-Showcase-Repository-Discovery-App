package xyz.kewiany.showcase.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import xyz.kewiany.showcase.details.GetRepositoryDetailsError.NoInternet
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Error
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.utils.DispatcherProvider
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.NavigationCommander

class DetailsViewModel(
    private val state: DetailsState,
    private val getRepositoryDetails: GetRepositoryDetails,
    private val navigationCommander: NavigationCommander,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    val isLoading: Flow<Boolean> = state.commonState.isLoading
    val name: Flow<String> = state.name
    val description: Flow<String> = state.description

    init {
        state.commonState.isLoading.value = false
        state.name.value = ""
        state.description.value = ""
        state.error.value = null
    }

    private suspend fun loadRepositoryDetails(id: Long) {
        when (val response = getRepositoryDetails(id)) {
            is Success -> {
                val item = response.repository
                state.name.value = item.name
                state.description.value = item.description
            }
            is Error -> {
                val error = if (response.error is NoInternet) ErrorType.NO_INTERNET else ErrorType.UNKNOWN
                state.error.value = error
            }
        }
    }

    fun load(id: Long) = viewModelScope.launch(dispatchers.main()) {
        state.error.value = null
        state.commonState.isLoading.value = true
        try {
            loadRepositoryDetails(id)
        } catch (e: CancellationException) {
            state.name.value = ""
            state.description.value = ""
        }
        state.commonState.isLoading.value = false
    }

    fun back() {
        navigationCommander.popBackStack()
    }
}