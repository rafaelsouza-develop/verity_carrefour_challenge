package com.example.githubrepos.data.service

import com.example.githubrepos.data.model.ReposResponse
import com.example.githubrepos.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>
    @GET("users/{repo}/repos")
    suspend fun getRepos(@Path("repo") repo: String): List<ReposResponse>
}