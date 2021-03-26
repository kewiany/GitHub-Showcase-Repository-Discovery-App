package xyz.kewiany.showcase.api

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import xyz.kewiany.showcase.list.Repository

class RepositoryService(private val path: String) : RepositoryApi {

    private val format = Json { ignoreUnknownKeys = true }

    override suspend fun getRepositories(query: String): RepositoriesResponse? {
        return try {
            val (_, _, result) = Fuel.get("$path/search/repositories?q=$query/")
                .awaitStringResponse()
            format.decodeFromString(result)
        } catch (e: FuelError) {
            throw e.exception
        }
    }

    override suspend fun getRepository(id: Long): Repository? {
        return try {
            val (_, _, result) = Fuel.get("$path/repositories/$id")
                .awaitStringResponse()
            format.decodeFromString(result)
        } catch (e: FuelError) {
            println(e)
            throw e.exception
        }
    }
}