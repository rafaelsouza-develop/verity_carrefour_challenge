package com.example.githubrepos.domain.repository

import com.example.githubrepos.domain.model.Repos
import com.example.githubrepos.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUsers(): Flow<List<User>>

    suspend fun geRepos(repo: String): Flow<List<Repos>>
}