package com.example.githubrepos.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.domain.model.User
import com.example.githubrepos.domain.repository.UsersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UsersRepository) : ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> = _viewState


    private var users = listOf<User>()


    fun dispatcherViewAction(action: HomeViewAction) {

        when (action) {
            is HomeViewAction.GetUsers -> getUsers()
        }
    }

    private fun getUsers() {
        viewModelScope.launch {
            _viewState.value = HomeViewState.Loading
            repository.getUsers()
                .catch { _viewState.value = HomeViewState.UsersLoadFailure(it) }
                .collect {
                    if (it.isNullOrEmpty()) {
                        _viewState.value = HomeViewState.UsersEmpty
                    } else {
                        users = it
                        _viewState.value = HomeViewState.UsersLoaded(it)
                    }
                }
        }
    }
}