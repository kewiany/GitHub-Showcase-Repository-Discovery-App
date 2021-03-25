package xyz.kewiany.showcase

import xyz.kewiany.showcase.api.RepositoriesResponse

val repositories = listOf(
    createRepository(), createRepository()
)
val repositoriesResponse = RepositoriesResponse(repositories)

