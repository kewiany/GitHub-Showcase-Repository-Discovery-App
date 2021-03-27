package xyz.kewiany.showcase.list

import com.nhaarman.mockitokotlin2.*
import io.kotest.matchers.shouldBe
import net.bytebuddy.utility.RandomString
import xyz.kewiany.showcase.CustomFreeSpec
import xyz.kewiany.showcase.api.RepositoryApi
import xyz.kewiany.showcase.list.GetRepositoriesError.NoInternet
import xyz.kewiany.showcase.list.GetRepositoriesError.Unknown
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Error
import xyz.kewiany.showcase.list.GetRepositoriesResponse.Success
import xyz.kewiany.showcase.utils.repositoriesResponse
import java.net.UnknownHostException

internal class GetRepositoriesTest : CustomFreeSpec({

    "on get repositories test" - {
        val api = mock<RepositoryApi>()
        val getRepositories: GetRepositories = GetRepositoriesImpl(api, testDispatcherProvider)
        val query = RandomString.make()

        var response: GetRepositoriesResponse?

        "get repositories" {
            response = getRepositories(query)
            verify(api).getRepositories(any())
        }

        "get repositories with success" - {
            whenever(api.getRepositories(any())) doReturn repositoriesResponse
            response = getRepositories(query)

            "return success" { response shouldBe Success(repositoriesResponse.repositories) }
        }

        "get repositories with no internet" - {
            whenever(api.getRepositories(any())).then { throw UnknownHostException() }
            response = getRepositories(query)

            "return error" { response shouldBe Error(NoInternet) }
        }

        "get repositories failing" - {
            whenever(api.getRepositories(any())).then { throw Exception() }
            response = getRepositories(query)

            "return error" { response shouldBe Error(Unknown) }
        }
    }
})