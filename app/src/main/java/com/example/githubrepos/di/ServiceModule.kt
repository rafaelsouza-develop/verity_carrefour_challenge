package com.example.githubrepos.di

import com.example.githubrepos.data.service.GithubService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    factory { get<Retrofit>().create(GithubService::class.java) }
}