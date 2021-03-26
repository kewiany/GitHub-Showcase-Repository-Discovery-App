package xyz.kewiany.showcase

import com.nhaarman.mockitokotlin2.*
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.list.GetRepositories
import xyz.kewiany.showcase.list.GetRepositoriesError.NoInternet
import xyz.kewiany.showcase.list.GetRepositoriesError.Unknown
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Error
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Success
import xyz.kewiany.showcase.list.ListState
import xyz.kewiany.showcase.list.ListViewModel
import xyz.kewiany.showcase.list.repositories
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.NavigationCommander

internal class ListViewModelTest : CustomFreeSpec({

    "on list view model test" - {
        val commonState = CommonState()
        val state = ListState(commonState)
        val getRepositories = mock<GetRepositories>()
        val navigationCommander = mock<NavigationCommander>()

        fun viewModel(): ListViewModel = ListViewModel(
            state,
            getRepositories,
            navigationCommander,
            testDispatcherProvider
        )

        "on init" - {
            viewModel()
            "set loading" { commonState.isLoading.value.shouldBeFalse() }
            "set items" { state.items.value.shouldBe(emptyList()) }
            "set no error" { state.error.value.shouldBeNull() }
            "get repositories" { verify(getRepositories).invoke() }
        }

        "on load" - {

            "on success response" - {
                whenever(getRepositories()) doReturn Success(repositories)
                viewModel()

                "set state" { state.items.value.shouldBe(repositories) }
            }

            "on error response" - {

                "on unknown error" - {
                    whenever(getRepositories()) doReturn Error(Unknown)
                    viewModel()

                    "set state" { state.error.value.shouldBe(ErrorType.UNKNOWN) }
                }

                "on no internet error" - {
                    whenever(getRepositories()) doReturn Error(NoInternet)
                    viewModel()

                    "set state" { state.error.value.shouldBe(ErrorType.NO_INTERNET) }
                }
            }
        }

        "on refresh" - {
            whenever(getRepositories()) doReturn Success(emptyList())
            viewModel().load()

            "get repositories" { verify(getRepositories, times(2)).invoke() }
            "set no error" { state.error.value.shouldBeNull() }
        }

        "on open details" - {
            viewModel().openDetails()

            "navigate" { verify(navigationCommander).navigate(R.id.action_listFragment_to_detailsFragment) }
        }
    }
})
