package xyz.kewiany.showcase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.details.DetailsState
import xyz.kewiany.showcase.details.DetailsViewModel
import xyz.kewiany.showcase.details.GetRepositoryDetails
import xyz.kewiany.showcase.details.GetRepositoryDetailsError
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Error
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.utils.ErrorType
import xyz.kewiany.showcase.utils.NavigationCommander

internal class DetailsViewModelTest : CustomFreeSpec({

    "on details view model test" - {
        val commonState = CommonState()
        val state = DetailsState(commonState)
        val getRepositoryDetails = mock<GetRepositoryDetails>()
        val navigationCommander = mock<NavigationCommander>()
        val id = 0L
        val repository = repositories.first()
        val viewModel = DetailsViewModel(state, getRepositoryDetails, navigationCommander, testDispatcherProvider)

        "on init" - {

            "set loading" { commonState.isLoading.value.shouldBeFalse() }
            "set name" { state.name.value.shouldBe("") }
            "set description" { state.description.value.shouldBe("") }
        }

        "on load" - {

            "on success response" - {
                whenever(getRepositoryDetails(id)) doReturn Success(repository)
                viewModel.load(id)

                "set name" { state.name.value.shouldBe(repository.name) }
                "set description" { state.description.value.shouldBe(repository.description) }
            }

            "on error response" - {

                "on unknown error" - {
                    whenever(getRepositoryDetails(id)) doReturn Error(GetRepositoryDetailsError.Unknown)
                    viewModel.load(id)

                    "set state" { state.error.value.shouldBe(ErrorType.UNKNOWN) }
                }

                "on no internet error" - {
                    whenever(getRepositoryDetails(id)) doReturn Error(GetRepositoryDetailsError.NoInternet)
                    viewModel.load(id)

                    "set state" { state.error.value.shouldBe(ErrorType.NO_INTERNET) }
                }
            }

            whenever(getRepositoryDetails(id)) doReturn Success(repositories.first())
            viewModel.load(id)
            "set no error" { state.error.value.shouldBeNull() }
        }

        "on back" - {
            viewModel.back()

            "navigate" { verify(navigationCommander).popBackStack() }
        }
    }
})
