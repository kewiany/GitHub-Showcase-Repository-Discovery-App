package xyz.kewiany.showcase.api

import xyz.kewiany.showcase.list.User

interface UserApi {
    suspend fun getFollowersForUser(name: String): List<User>?
    suspend fun getUser(name: String): User?
}