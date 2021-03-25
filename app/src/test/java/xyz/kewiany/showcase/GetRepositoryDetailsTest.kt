package xyz.kewiany.showcase

import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.details.GetRepositoryDetails
import xyz.kewiany.showcase.details.GetRepositoryDetailsImpl
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.list.repositories

internal class GetRepositoryDetailsTest : CustomFreeSpec({

    "on get repository details test" - {
        val getRepositoryDetails: GetRepositoryDetails = GetRepositoryDetailsImpl(testDispatcherProvider)

        var response: GetRepositoryDetailsResponse? = null
        response = getRepositoryDetails(0)

        "get repository details with success" {
            response shouldBe Success(repositories.first())
        }
    }
})