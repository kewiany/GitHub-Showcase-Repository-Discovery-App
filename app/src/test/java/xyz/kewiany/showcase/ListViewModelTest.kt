package xyz.kewiany.showcase

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.list.ListState
import xyz.kewiany.showcase.list.ListViewModel

internal class ListViewModelTest : CustomFreeSpec({

    "on list view model test" - {
        val commonState = CommonState()
        val state = ListState(commonState)
        val viewModel = ListViewModel(state)

        "on init" - {

            "set loading" { commonState.isLoading.value.shouldBeTrue() }
            "set items" { state.items.value.shouldBe(listOf("", "")) }
        }
    }
})
