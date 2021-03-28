package xyz.kewiany.showcase.utils

import net.bytebuddy.utility.RandomString
import xyz.kewiany.showcase.entity.Repository
import xyz.kewiany.showcase.entity.User
import kotlin.random.Random

fun createRepository(
    id: Long = Random.nextLong(),
    name: String = RandomString.make(),
    description: String = RandomString.make(),
    language: String = RandomString.make(),
    stars: Int = Random.nextInt(),
    watchers: Int = Random.nextInt(),
    forks: Int = Random.nextInt(),
    updatedAt: String = RandomString.make(),
    createdAt: String = RandomString.make(),
    user: User = createUser()
) = Repository(
    id,
    name,
    description,
    language,
    stars,
    watchers,
    forks,
    updatedAt,
    createdAt,
    user
)

fun createUser(
    name: String = RandomString.make(),
    avatar: String = RandomString.make(),
    followers: Int = Random.nextInt()
) = User(name, avatar, followers)