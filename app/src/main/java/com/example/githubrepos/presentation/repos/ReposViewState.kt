package com.example.githubrepos.presentation.repos

import com.example.githubrepos.domain.model.Repos

sealed class ReposViewState {

    data object Loading : ReposViewState()
    data object ReposEmpty : ReposViewState()
    data class ReposLoaded(val repos: List<Repos>) : ReposViewState()
    data class ReposLoadFailure(val error: Throwable) : ReposViewState()
}