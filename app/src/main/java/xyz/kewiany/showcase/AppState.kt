package xyz.kewiany.showcase

import kotlinx.coroutines.flow.MutableStateFlow
import xyz.kewiany.showcase.details.DetailsState
import xyz.kewiany.showcase.list.ListState

class AppState {
    val commonState = CommonState()
    val listState = ListState(commonState)
    val detailsState = DetailsState(commonState)
}

class CommonState {
    val isLoading = MutableStateFlow(false)
}