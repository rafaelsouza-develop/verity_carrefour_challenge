package com.example.githubrepos.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.databinding.FragmentHomeBinding
import com.example.githubrepos.domain.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), HomeAdapter.UserAdapterListner {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_home)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables(viewModel)
        viewModel.dispatcherViewAction(HomeViewAction.GetUsers)
    }
    private fun setupObservables(viewModel: HomeViewModel) {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->

            when (viewState) {
                is HomeViewState.UsersLoaded -> {
                    showSucessState()
                    setRecyclerViewList(viewState.movies)
                }

                HomeViewState.Loading -> {
                    showLoadingState()
                }

                is HomeViewState.UsersLoadFailure -> {
                    goToErrorView()
                }

                HomeViewState.UsersEmpty -> {
                    goToEmptyView()
                }
            }
        }
    }

    private fun goToEmptyView() {
        findNavController().navigate(R.id.action_homeFragment_to_emptyFragment)
    }

    private fun goToErrorView() {
        findNavController().navigate(R.id.action_homeFragment_to_errorFragment)
    }

    private fun setRecyclerViewList(users: List<User>) {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(users)
        }
    }

    override fun goToReposList(user: User) {

        findNavController().navigate(R.id.action_homeFragment_to_reposFragment)
    }

    private fun showLoadingState() {
        with(binding) {
            rvUsers.visibility = View.GONE
            containerLoadingState.visibility = View.VISIBLE
        }
    }

    private fun showSucessState() {
        with(binding) {
            rvUsers.visibility = View.VISIBLE
            containerLoadingState.visibility = View.GONE
        }
    }
}
