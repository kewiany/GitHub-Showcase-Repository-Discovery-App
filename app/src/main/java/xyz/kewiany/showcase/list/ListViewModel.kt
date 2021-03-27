package xyz.kewiany.showcase.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.entity.Repository
import xyz.kewiany.showcase.list.GetRepositoriesError.NoInternet
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Error
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Success
import xyz.kewiany.showcase.utils.Constant.REPOSITORY_KEY
import xyz.kewiany.showcase.utils.ContentType
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
    val content: Flow<ContentType?> = state.content

    private var cachedQuery: String = ""
    private var queryChangedJob: Job? = null

    init {
        state.commonState.isLoading.value = false
        state.items.value = emptyList()
        state.error.value = null
        state.content.value = ContentType.START
    }

    fun updateQuery(query: String) {
        if (query == cachedQuery) return
        queryChangedJob?.cancel()
        queryChangedJob = viewModelScope.launch(dispatchers.main()) {
            delay(DEBOUNCE_DELAY_IN_MILLI_SECONDS)
            cachedQuery = query
            load(query)
        }
    }

    fun refresh() {
        load(cachedQuery)
    }

    private fun load(query: String) = viewModelScope.launch(dispatchers.main()) {
        state.content.value = null
        state.error.value = null
        state.commonState.isLoading.value = true
        try {
            if (query.isNotEmpty()) {
                loadRepositories(query)
            } else {
                state.content.value = ContentType.START
                state.items.value = emptyList()
            }
        } catch (e: CancellationException) {
            state.items.value = emptyList()
        }
        state.commonState.isLoading.value = false
    }

    private suspend fun loadRepositories(query: String) {
        when (val response = getRepositories(query)) {
            is Success -> {
                val items = response.repositories
                state.content.value = if (items.isEmpty()) ContentType.EMPTY else null
                state.items.value = response.repositories
            }
            is Error -> {
                val error = if (response.error is NoInternet) ErrorType.NO_INTERNET else ErrorType.UNKNOWN
                state.error.value = error
            }
        }
    }

    fun openDetails(id: Long) {
        state.error.value = null
        navigationCommander.navigate(R.id.action_listFragment_to_detailsFragment, REPOSITORY_KEY to id)
    }
}

private const val DEBOUNCE_DELAY_IN_MILLI_SECONDS = 500L