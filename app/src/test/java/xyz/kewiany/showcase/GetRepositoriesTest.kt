package xyz.kewiany.showcase

import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.list.GetRepositories
import xyz.kewiany.showcase.list.GetRepositoriesImpl
import xyz.kewiany.showcase.list.GetRepositoriesResponse
import xyz.kewiany.showcase.list.repositories

internal class GetRepositoriesTest : CustomFreeSpec({

    "on get repositories test" - {
        val getRepositories: GetRepositories = GetRepositoriesImpl(testDispatcherProvider)

        var response: GetRepositoriesResponse? = null
        response = getRepositories()

        "get repositories with success" {
            response shouldBe GetRepositoriesResponse.Success(repositories)
        }
    }
})