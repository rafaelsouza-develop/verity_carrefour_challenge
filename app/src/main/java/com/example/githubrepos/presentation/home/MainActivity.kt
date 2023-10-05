package com.example.githubrepos.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.databinding.ActivityMainBinding
import com.example.githubrepos.domain.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupObservables(viewModel)
        viewModel.dispatcherViewAction(HomeViewAction.GetUsers)
    }

    private fun setupObservables(viewModel: HomeViewModel) {
        viewModel.viewState.observe(this) { viewState ->

            when (viewState) {
                is HomeViewState.UsersLoaded -> {
                    //showSucessState()
                    setRecyclerViewList(viewState.movies)
                }

                HomeViewState.Loading -> {
                    //showLoadingState()
                }

                is HomeViewState.UsersLoadFailure -> {
                    //goToErrorView()
                }

                HomeViewState.UsersEmpty -> {
                    //goToEmptyView()
                }
            }
        }
    }

    private fun setRecyclerViewList(users: List<User>) {
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(users)
        }
    }
}