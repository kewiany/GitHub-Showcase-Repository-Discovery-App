package xyz.kewiany.showcase.list

import kotlinx.coroutines.flow.MutableStateFlow
import xyz.kewiany.showcase.CommonState
import xyz.kewiany.showcase.entity.Repository
import xyz.kewiany.showcase.utils.ContentType
import xyz.kewiany.showcase.utils.ErrorType

class ListState(val commonState: CommonState) {
    val items = MutableStateFlow(emptyList<Repository>())
    val error = MutableStateFlow<ErrorType?>(null)
    val content = MutableStateFlow<ContentType?>(null)
}