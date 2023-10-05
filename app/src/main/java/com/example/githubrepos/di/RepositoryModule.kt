package com.example.githubrepos.di

import com.example.githubrepos.data.repository.UsersRepositoryImpl
import com.example.githubrepos.domain.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UsersRepository> { UsersRepositoryImpl(userService = get()) }
}