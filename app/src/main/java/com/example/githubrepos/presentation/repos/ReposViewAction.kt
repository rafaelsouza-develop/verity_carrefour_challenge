package com.example.githubrepos.presentation.repos

sealed class ReposViewAction {

    data class GetRepos(val login: String) : ReposViewAction()
}