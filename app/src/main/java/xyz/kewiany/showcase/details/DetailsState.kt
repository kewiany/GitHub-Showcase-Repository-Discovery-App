package xyz.kewiany.showcase.details

import kotlinx.coroutines.flow.MutableStateFlow
import xyz.kewiany.showcase.CommonState
import xyz.kewiany.showcase.entity.User
import xyz.kewiany.showcase.utils.ErrorType

class DetailsState(val commonState: CommonState) {
    val name = MutableStateFlow("")
    val description = MutableStateFlow("")
    val stars = MutableStateFlow("")
    val watchers = MutableStateFlow("")
    val forks = MutableStateFlow("")
    val updatedAt = MutableStateFlow("")
    val createdAt = MutableStateFlow("")
    val language = MutableStateFlow("")
    val owner = MutableStateFlow("")
    val avatar = MutableStateFlow("")
    val followers = MutableStateFlow<List<User>>(emptyList())
    val error = MutableStateFlow<ErrorType?>(null)
}