package xyz.kewiany.showcase

import net.bytebuddy.utility.RandomString
import xyz.kewiany.showcase.list.Repository
import kotlin.random.Random

fun createRepository(
    id: Long = Random.nextLong(),
    name: String = RandomString.make(),
    description: String = RandomString.make()
) = Repository(id, name, description)