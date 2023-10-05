package com.example.githubrepos.data.repository

import com.example.githubrepos.data.service.GithubService
import com.example.githubrepos.domain.mapper.toDomain
import com.example.githubrepos.domain.mapper.toRepos
import com.example.githubrepos.domain.model.Repos
import com.example.githubrepos.domain.model.User
import com.example.githubrepos.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UsersRepositoryImpl(private val userService: GithubService) : UsersRepository {
    override suspend fun getUsers(): Flow<List<User>> = flow {
        val response = userService.getUsers()
        emit(response.map {
            it.toDomain()
        })
    }

    override suspend fun geRepos(repo: String): Flow<List<Repos>>  = flow {
        val response = userService.getRepos(repo)
        emit(response.map {
            it.toRepos()
        })
    }

}