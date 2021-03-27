package xyz.kewiany.showcase.list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import net.bytebuddy.utility.RandomString
import xyz.kewiany.showcase.CommonState
import xyz.kewiany.showcase.CustomFreeSpec
import xyz.kewiany.showcase.R
import xyz.kewiany.showcase.list.GetRepositoriesError.NoInternet
import xyz.kewiany.showcase.list.GetRepositoriesError.Unknown
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Error
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Success
import xyz.kewiany.showcase.utils.*
import kotlin.coroutines.resume

internal class ListViewModelTest : CustomFreeSpec({

    "on list view model test" - {
        val commonState = CommonState()
        val state = ListState(commonState)
        val getRepositories = MockGetRepositories()
        val navigationCommander = mock<NavigationCommander>()
        val query = RandomString.make()

        fun viewModel(): ListViewModel = ListViewModel(
            state,
            getRepositories,
            navigationCommander,
            testDispatcherProvider
        )

        "on init" - {
            viewModel()

            "set items" { state.items.value.shouldBe(emptyList()) }
            "set no error" { state.error.value.shouldBeNull() }
            "get repositories" { getRepositories.get.invocations.count() shouldBe 0 }
            "set no loading" { commonState.isLoading.value.shouldBeFalse() }
        }

        "on load" - {

            "on success response" - {
                with(viewModel()) {
                    updateQuery(query)
                }
                testScope.advanceTimeBy(500L)

                "set loading" { commonState.isLoading.value.shouldBeTrue() }
                getRepositories.get.resume(Success(repositories))
                "set state" { state.items.value.shouldBe(repositories) }
                "set no loading" { commonState.isLoading.value.shouldBeFalse() }
            }

            "on error response" - {
                listOf(Unknown to ErrorType.UNKNOWN, NoInternet to ErrorType.NO_INTERNET).forEach { (error, errorType) ->
                    "on $error" - {
                        with(viewModel()) {
                            updateQuery(query)
                        }
                        testScope.advanceTimeBy(500L)

                        "set loading" { commonState.isLoading.value.shouldBeTrue() }
                        getRepositories.get.resume(Error(error))
                        "set state" { state.error.value.shouldBe(errorType) }
                        "set no loading" { commonState.isLoading.value.shouldBeFalse() }
                    }
                }
            }
        }

        "on update query" - {
            with(viewModel()) {
                updateQuery(query)

                "after 100 milliseconds" - {
                    testScope.advanceTimeBy(100L)
                    "get repositories" { getRepositories.get.invocations.count() shouldBe 0 }

                    "on update query" - {
                        updateQuery(query)
                        testScope.advanceTimeBy(500L)
                        "get repositories" { getRepositories.get.invocations.count() shouldBe 1 }
                    }
                }

                "after 500 milliseconds" - {
                    testScope.advanceTimeBy(500L)
                    "get repositories" { getRepositories.get.invocations.count() shouldBe 1 }
                    getRepositories.get.resume(Success(emptyList()))

                    "on update same query" - {
                        updateQuery(query)
                        testScope.advanceTimeBy(500L)
                        "get repositories" { getRepositories.get.invocations.count() shouldBe 1 }
                    }

                    "on update different query" - {
                        updateQuery(query + query)
                        testScope.advanceTimeBy(500L)
                        "get repositories" { getRepositories.get.invocations.count() shouldBe 2 }
                    }
                }
            }
        }

        "on refresh" - {
            with(viewModel()) {
                refresh()
            }

            "set no error" { state.error.value.shouldBeNull() }
            "set loading" { commonState.isLoading.value.shouldBeTrue() }
            "get repositories" { getRepositories.get.invocations.count() shouldBe 1 }
            getRepositories.get.resume(Success(repositories))
            "set no loading" { commonState.isLoading.value.shouldBeFalse() }
        }

        "on open details" - {
            val id = repository.id
            val bundle = Constant.REPOSITORY_KEY to id
            viewModel().openDetails(id)

            "navigate" { verify(navigationCommander).navigate(R.id.action_listFragment_to_detailsFragment, bundle) }
        }
    }
})
