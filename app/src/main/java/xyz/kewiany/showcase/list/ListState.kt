package xyz.kewiany.showcase.list

import kotlinx.coroutines.flow.MutableStateFlow
import xyz.kewiany.showcase.CommonState

class ListState(val commonState: CommonState) {
    val items = MutableStateFlow(emptyList<String>())
}