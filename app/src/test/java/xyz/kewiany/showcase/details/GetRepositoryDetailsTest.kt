package xyz.kewiany.showcase.details

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.CustomFreeSpec
import xyz.kewiany.showcase.api.RepositoryApi
import xyz.kewiany.showcase.details.GetRepositoryDetailsError.NoInternet
import xyz.kewiany.showcase.details.GetRepositoryDetailsError.Unknown
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Error
import xyz.kewiany.showcase.details.GetRepositoryDetailsResponse.Success
import xyz.kewiany.showcase.utils.repository
import java.net.UnknownHostException

internal class GetRepositoryDetailsTest : CustomFreeSpec({

    "on get repository details test" - {
        val api = mock<RepositoryApi>()
        val getRepositoryDetails: GetRepositoryDetails = GetRepositoryDetailsImpl(api, testDispatcherProvider)
        val repository = repository
        val id = repository.id
        var response: GetRepositoryDetailsResponse? = null

        suspend fun getRepository(id: Long) {
            response = getRepositoryDetails(id)
        }

        "get repository" {
            getRepository(id)
            verify(api).getRepository(id)
        }

        "get repository details with success" - {
            whenever(api.getRepository(id)) doReturn repository
            getRepository(id)

            "return success" { response shouldBe Success(repository) }
        }

        "get repositories with no internet" - {
            whenever(api.getRepository(id)).then { throw UnknownHostException() }
            getRepository(id)

            "return error" { response shouldBe Error(NoInternet) }
        }

        "get repositories failing" - {
            whenever(api.getRepository(id)).then { throw Exception() }
            getRepository(id)

            "return error" { response shouldBe Error(Unknown) }
        }
    }
})