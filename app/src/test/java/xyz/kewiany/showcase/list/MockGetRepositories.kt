package xyz.kewiany.showcase.list

import pl.mareklangiewicz.smokk.smokk

class MockGetRepositories : GetRepositories {

    val get = smokk<GetRepositoriesResponse>()

    override suspend fun invoke(): GetRepositoriesResponse = get.invoke()
}