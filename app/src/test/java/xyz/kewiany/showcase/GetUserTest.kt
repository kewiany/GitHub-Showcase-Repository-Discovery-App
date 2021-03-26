package xyz.kewiany.showcase

import com.nhaarman.mockitokotlin2.*
import io.kotest.matchers.shouldBe
import xyz.kewiany.showcase.api.UserApi
import xyz.kewiany.showcase.details.GetUser
import xyz.kewiany.showcase.details.GetUserError.NoInternet
import xyz.kewiany.showcase.details.GetUserError.Unknown
import xyz.kewiany.showcase.details.GetUserImpl
import xyz.kewiany.showcase.details.GetUserResponse
import xyz.kewiany.showcase.details.GetUserResponse.Error
import xyz.kewiany.showcase.details.GetUserResponse.Success
import java.net.UnknownHostException

internal class GetUserTest : CustomFreeSpec({

    "on get user test" - {
        val api = mock<UserApi>()
        val name = "userName"
        val getUser: GetUser = GetUserImpl(api, testDispatcherProvider)
        var response: GetUserResponse? = null

        suspend fun get(name: String) {
            response = getUser(name)
        }

        "get" - {
            whenever(api.getUser(name)) doReturn user
            whenever(api.getFollowersForUser(name)) doReturn users
            get(name)

            "get user" { verify(api).getUser(name) }
            "get followers" { verify(api).getFollowersForUser(name) }
        }

        "get user failing and get followers successfully" - {
            whenever(api.getUser(name)).then { throw Exception() }
            whenever(api.getFollowersForUser(name)) doReturn users
            get(name)

            "do not get followers" { verify(api, never()).getFollowersForUser(name) }
            "return error" { response shouldBe Error(Unknown) }
        }

        "get user successfully and get followers failing" - {
            whenever(api.getUser(name)) doReturn user
            whenever(api.getFollowersForUser(name)).then { throw Exception() }
            get(name)

            "return error" { response shouldBe Error(Unknown) }
        }

        "get user with success" - {
            whenever(api.getUser(name)) doReturn user
            whenever(api.getFollowersForUser(name)) doReturn users
            get(name)

            "return success" { response shouldBe Success(user, users) }
        }

        "get user with no internet" - {
            whenever(api.getUser(name)).then { throw UnknownHostException() }
            get(name)

            "do not get followers" { verify(api, never()).getFollowersForUser(name) }
            "return error" { response shouldBe Error(NoInternet) }
        }
    }
})