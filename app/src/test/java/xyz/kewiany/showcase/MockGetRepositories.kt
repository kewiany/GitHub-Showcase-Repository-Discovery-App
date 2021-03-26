package xyz.kewiany.showcase

import pl.mareklangiewicz.smokk.smokk
import xyz.kewiany.showcase.list.GetRepositories
import xyz.kewiany.showcase.list.GetRepositoriesResponse

class MockGetRepositories : GetRepositories {

    val get = smokk<GetRepositoriesResponse>()

    override suspend fun invoke(): GetRepositoriesResponse = get.invoke()
}