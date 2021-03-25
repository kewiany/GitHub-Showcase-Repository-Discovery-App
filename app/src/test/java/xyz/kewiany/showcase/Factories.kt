package xyz.kewiany.showcase

import net.bytebuddy.utility.RandomString
import xyz.kewiany.showcase.list.Repository

fun createRepository(
    name: String = RandomString.make(),
    description: String = RandomString.make()
) = Repository(name, description)