package xyz.kewiany.showcase.utils

import net.bytebuddy.utility.RandomString
import xyz.kewiany.showcase.list.Repository
import xyz.kewiany.showcase.list.User
import kotlin.random.Random

fun createRepository(
    id: Long = Random.nextLong(),
    name: String = RandomString.make(),
    description: String = RandomString.make(),
    user: User = createUser()
) = Repository(id, name, description, user)

fun createUser(
    name: String = RandomString.make(),
    avatar: String = RandomString.make(),
    followers: Int = Random.nextInt()
) = User(name, avatar, followers)