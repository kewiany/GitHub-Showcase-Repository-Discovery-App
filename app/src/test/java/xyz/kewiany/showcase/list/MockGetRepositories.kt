package xyz.kewiany.showcase.list

import pl.mareklangiewicz.smokk.smokk

class MockGetRepositories : GetRepositories {

    val get = smokk<String, GetRepositoriesResponse>()

    override suspend fun invoke(query: String): GetRepositoriesResponse = get.invoke(query)
}