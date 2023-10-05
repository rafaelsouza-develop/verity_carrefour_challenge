package com.example.githubrepos.presentation.home

import com.example.githubrepos.domain.model.User

sealed class HomeViewState {

    data object Loading : HomeViewState()
    data object UsersEmpty : HomeViewState()
    data class UsersLoaded(val movies: List<User>) : HomeViewState()
    data class UsersLoadFailure(val error: Throwable) : HomeViewState()
}