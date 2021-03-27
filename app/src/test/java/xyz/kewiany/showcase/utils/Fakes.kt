package xyz.kewiany.showcase.utils

import xyz.kewiany.showcase.api.RepositoriesResponse

val repositories = listOf(
    createRepository(0, "name", "description"), createRepository()
)
val repository = repositories[0]
val repositoriesResponse = RepositoriesResponse(repositories)

val users = listOf(
    createUser(), createUser(), createUser()
)

val user = createUser()