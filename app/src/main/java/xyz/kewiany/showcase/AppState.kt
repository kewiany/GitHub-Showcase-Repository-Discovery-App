package xyz.kewiany.showcase

import kotlinx.coroutines.flow.MutableStateFlow
import xyz.kewiany.showcase.list.ListState

class AppState {
    val commonState = CommonState()
    val listState = ListState(commonState)
}

class CommonState {
    val isLoading = MutableStateFlow(false)
}