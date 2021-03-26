package xyz.kewiany.showcase.details

import kotlinx.coroutines.flow.MutableStateFlow
import xyz.kewiany.showcase.CommonState
import xyz.kewiany.showcase.list.User
import xyz.kewiany.showcase.utils.ErrorType

class DetailsState(val commonState: CommonState) {
    val name = MutableStateFlow("")
    val description = MutableStateFlow("")
    val userName = MutableStateFlow("")
    val followers = MutableStateFlow<List<User>>(emptyList())
    val error = MutableStateFlow<ErrorType?>(null)
}