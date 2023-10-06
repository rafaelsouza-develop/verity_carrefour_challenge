package com.example.githubrepos.presentation.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.domain.model.Repos
import com.example.githubrepos.domain.repository.UsersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ReposViewModel(private val repository: UsersRepository) : ViewModel() {

    private val _viewState = MutableLiveData<ReposViewState>()
    val viewState: LiveData<ReposViewState> = _viewState

    private var repos = listOf<Repos>()

    fun dispatcherViewAction(action: ReposViewAction) {

        when (action) {
            is ReposViewAction.GetRepos -> getRepos(action.login)
        }
    }

    private fun getRepos(login: String) {
        viewModelScope.launch {
            _viewState.value = ReposViewState.Loading
            repository.geRepos(login)
                .catch { _viewState.value = ReposViewState.ReposLoadFailure(it) }
                .collect {
                    if (it.isNullOrEmpty()) {
                        _viewState.value = ReposViewState.ReposEmpty
                    } else {
                        repos = it
                        _viewState.value = ReposViewState.ReposLoaded(it)
                    }
                }
        }
    }


}