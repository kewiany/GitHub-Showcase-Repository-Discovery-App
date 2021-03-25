package xyz.kewiany.showcase

import com.nhaarman.mockitokotlin2.*
import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.api.RepositoryApi
import xyz.kewiany.showcase.list.GetRepositories
import xyz.kewiany.showcase.list.GetRepositoriesError.NoInternet
import xyz.kewiany.showcase.list.GetRepositoriesError.Unknown
import xyz.kewiany.showcase.list.GetRepositoriesImpl
import xyz.kewiany.showcase.list.GetRepositoriesResponse
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Error
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Success
import java.net.UnknownHostException

internal class GetRepositoriesTest : CustomFreeSpec({

    "on get repositories test" - {
        val api = mock<RepositoryApi>()
        val getRepositories: GetRepositories = GetRepositoriesImpl(api, testDispatcherProvider)

        var response: GetRepositoriesResponse?

        "get repositories" {
            response = getRepositories()
            verify(api).getRepositories(any())
        }

        "get repositories with success" - {
            whenever(api.getRepositories(any())) doReturn repositoriesResponse
            response = getRepositories()

            "return success" { response shouldBe Success(repositoriesResponse.repositories) }
        }

        "get repositories with no internet" - {
            whenever(api.getRepositories(any())).then { throw UnknownHostException() }
            response = getRepositories()

            "return error" { response shouldBe Error(NoInternet) }
        }

        "get repositories failing" - {
            whenever(api.getRepositories(any())).then { throw Exception() }
            response = getRepositories()

            "return error" { response shouldBe Error(Unknown) }
        }
    }
})