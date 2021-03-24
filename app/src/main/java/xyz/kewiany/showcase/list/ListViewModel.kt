package xyz.kewiany.showcase.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class ListViewModel(listState: ListState) : ViewModel() {

    val isLoading: Flow<Boolean> = listState.commonState.isLoading
    val items: Flow<List<String>> = listState.items

    init {
        listState.commonState.isLoading.value = true
        listState.items.value = listOf("", "")
    }
}
