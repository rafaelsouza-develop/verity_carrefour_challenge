package com.example.githubrepos.di

import com.example.githubrepos.presentation.home.HomeViewModel
import com.example.githubrepos.presentation.repos.ReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(repository = get()) }
    viewModel { ReposViewModel(repository = get()) }
}