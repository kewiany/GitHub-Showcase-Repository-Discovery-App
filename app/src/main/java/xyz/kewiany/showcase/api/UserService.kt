package xyz.kewiany.showcase.api

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import xyz.kewiany.showcase.list.User

class UserService(private val path: String) : UserApi {

    private val format = Json { ignoreUnknownKeys = true }

    override suspend fun getFollowersForUser(name: String): List<User>? {
        return try {
            val (_, _, result) = Fuel.get("$path/$name/followers")
                .awaitStringResponse()
            format.decodeFromString(result)
        } catch (e: FuelError) {
            throw e.exception
        }
    }

    override suspend fun getUser(name: String): User? {
        return try {
            val (_, _, result) = Fuel.get("$path/$name")
                .awaitStringResponse()
            format.decodeFromString(result)
        } catch (e: FuelError) {
            println(e)
            throw e.exception
        }
    }
}