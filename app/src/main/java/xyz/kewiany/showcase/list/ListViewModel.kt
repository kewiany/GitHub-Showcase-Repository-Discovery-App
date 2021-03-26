package xyz.kewiany.showcase.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.list.GetRepositoriesError.NoInternet
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Error
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Success
import xyz.kewiany.showcase.utils.Constant.REPOSITORY_KEY
import xyz.kewiany.showcase.utils.DispatcherProvider
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.NavigationCommander

class ListViewModel(
    private val state: ListState,
    private val getRepositories: GetRepositories,
    private val navigationCommander: NavigationCommander,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    val isLoading: Flow<Boolean> = state.commonState.isLoading
    val items: Flow<List<Repository>> = state.items
    val error: Flow<ErrorType?> = state.error

    init {
        state.commonState.isLoading.value = false
        state.items.value = emptyList()
        state.error.value = null
        load()
    }

    private suspend fun loadRepositories() {
        when (val response = getRepositories()) {
            is Success -> {
                state.items.value = response.repositories
            }
            is Error -> {
                val error = if (response.error is NoInternet) ErrorType.NO_INTERNET else ErrorType.UNKNOWN
                state.error.value = error
            }
        }
    }

    fun load() = viewModelScope.launch(dispatchers.main()) {
        state.error.value = null
        state.commonState.isLoading.value = true
        try {
            loadRepositories()
        } catch (e: CancellationException) {
            state.items.value = emptyList()
        }
        state.commonState.isLoading.value = false
    }

    fun openDetails(id: Long) {
        state.error.value = null
        navigationCommander.navigate(R.id.action_listFragment_to_detailsFragment, REPOSITORY_KEY to id)
    }
}