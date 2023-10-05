package com.example.githubrepos.presentation.home

sealed class HomeViewAction {
    data object GetUsers : HomeViewAction()
}