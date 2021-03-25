package xyz.kewiany.showcase.api

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RepositoryService(private val path: String) : RepositoryApi {

    private val format = Json { ignoreUnknownKeys = true }

    override suspend fun getRepositories(query: String): RepositoriesResponse? {
        return try {
            val (_, _, result) = Fuel.get("$path/repositories?q=$query/")
                .awaitStringResponse()
            format.decodeFromString(result)
        } catch (e: FuelError) {
            throw e.exception
        }
    }
}