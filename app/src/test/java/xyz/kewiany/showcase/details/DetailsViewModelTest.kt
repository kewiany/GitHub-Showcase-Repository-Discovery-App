package xyz.kewiany.showcase.details

import com.nhaarman.mockitokotlin2.*
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import net.bytebuddy.utility.RandomString
import xyz.kewiany.showcase.CommonState
import xyz.kewiany.showcase.CustomFreeSpec
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Error
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.details.GetUserError.NoInternet
import xyz.kewiany.showcase.details.GetUserError.Unknown
import xyz.kewiany.showcase.utils.*

internal class DetailsViewModelTest : CustomFreeSpec({

    "on details view model test" - {
        val commonState = CommonState()
        val state = DetailsState(commonState)
        val getRepositoryDetails = mock<GetRepositoryDetails>()
        val getUser = mock<GetUser>()
        val navigationCommander = mock<NavigationCommander>()
        val dateTime = RandomString.make()
        val dateTimeFormatter = mock<DateTimeFormatter> {
            on { format(any()) } doReturn dateTime
        }
        val id = 0L
        val repository = repositories.first()
        val user = repository.user
        val viewModel = DetailsViewModel(
            state,
            getRepositoryDetails,
            getUser,
            navigationCommander,
            dateTimeFormatter,
            testDispatcherProvider
        )

        "on init" - {

            "set loading" { commonState.isLoading.value.shouldBeFalse() }
            "set name" { state.name.value.shouldBe("") }
            "set description" { state.description.value.shouldBe("") }
            "set stars" { state.stars.value.shouldBe("") }
            "set watchers" { state.watchers.value.shouldBe("") }
            "set forks" { state.forks.value.shouldBe("") }
            "set updatedAt" { state.updatedAt.value.shouldBe("") }
            "set createdAt" { state.createdAt.value.shouldBe("") }
            "set language" { state.language.value.shouldBe("") }
            "set owner" { state.owner.value.shouldBe("") }
            "set avatar" { state.avatar.value.shouldBe("") }
            "set followers" { state.followers.value.shouldBeEmpty() }
            "set error" { state.error.value.shouldBeNull() }
        }

        "on load" - {

            "on repository details" - {

                "on success response" - {
                    whenever(getRepositoryDetails(id)) doReturn Success(repository)
                    viewModel.load(id)

                    "get repository details" { verify(getRepositoryDetails).invoke(id) }
                    "get user" { verify(getUser).invoke(user.name) }
                }

                "on error response" - {

                    "on unknown error" - {
                        whenever(getRepositoryDetails(id)) doReturn Error(GetRepositoryDetailsError.Unknown)
                        viewModel.load(id)

                        "set state" { state.error.value.shouldBe(ErrorType.UNKNOWN) }
                        "do not get user" { verify(getUser, never()).invoke(user.name) }
                    }

                    "on no internet error" - {
                        whenever(getRepositoryDetails(id)) doReturn Error(GetRepositoryDetailsError.NoInternet)
                        viewModel.load(id)

                        "set state" { state.error.value.shouldBe(ErrorType.NO_INTERNET) }
                        "do not get user" { verify(getUser, never()).invoke(user.name) }
                    }
                }
            }

            "on user" - {
                "on error response" - {
                    whenever(getRepositoryDetails(id)) doReturn Success(repository)

                    "on unknown error" - {
                        whenever(getUser(user.name)) doReturn GetUserResponse.Error(Unknown)
                        viewModel.load(id)

                        "set state" { state.error.value.shouldBe(ErrorType.UNKNOWN) }
                    }

                    "on no internet error" - {
                        whenever(getUser(user.name)) doReturn GetUserResponse.Error(NoInternet)
                        viewModel.load(id)

                        "set state" { state.error.value.shouldBe(ErrorType.NO_INTERNET) }
                    }
                }
            }

            whenever(getRepositoryDetails(id)) doReturn Success(repository)
            whenever(getUser(user.name)) doReturn GetUserResponse.Success(user, users)
            viewModel.load(id)
            "set no error" { state.error.value.shouldBeNull() }
            "set name" { state.name.value.shouldBe(repository.name) }
            "set description" { state.description.value.shouldBe(repository.description) }
            "set stars" { state.stars.value.shouldBe(repository.stars.toString()) }
            "set watchers" { state.watchers.value.shouldBe(repository.watchers.toString()) }
            "set forks" { state.forks.value.shouldBe(repository.forks.toString()) }
            "set updatedAt" { state.updatedAt.value.shouldBe(dateTime) }
            "set createdAt" { state.createdAt.value.shouldBe(dateTime) }
            "set language" { state.language.value.shouldBe(repository.language) }
            "set owner" { state.owner.value.shouldBe(user.name) }
            "set avatar" { state.avatar.value.shouldBe(user.avatar) }
            "set followers" { state.followers.value.shouldBe(users) }

            "on load again" - {
                viewModel.load(id)

                "do not get repository details again" { verify(getRepositoryDetails, times(1)).invoke(id) }
                "do not get user again" { verify(getUser, times(1)).invoke(user.name) }
            }

            "on refresh" - {
                viewModel.refresh(id)

                "get repository details" { verify(getRepositoryDetails, times(2)).invoke(id) }
                "get user" { verify(getUser, times(2)).invoke(user.name) }
            }
        }

        "on back" - {
            viewModel.back()

            "navigate" { verify(navigationCommander).popBackStack() }
        }
    }
})
