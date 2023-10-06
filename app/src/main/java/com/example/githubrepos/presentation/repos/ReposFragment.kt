package com.example.githubrepos.presentation.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.databinding.FragmentReposBinding
import com.example.githubrepos.domain.model.Repos
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReposFragment : Fragment() {

    private val viewModel: ReposViewModel by viewModel()
    private lateinit var binding: FragmentReposBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_repos)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables(viewModel)
        viewModel.dispatcherViewAction(ReposViewAction.GetRepos(""))
    }

    private fun setupObservables(viewModel: ReposViewModel) {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->

            when (viewState) {
                is ReposViewState.ReposLoaded -> {
                    showSucessState()
                    setRecyclerViewList(viewState.repos)
                }

                ReposViewState.Loading -> {
                    showLoadingState()
                }

                is ReposViewState.ReposLoadFailure -> {
                    goToErrorView()
                }

                ReposViewState.ReposEmpty -> {
                    goToEmptyView()
                }
            }
        }
    }

    private fun goToErrorView() {
        findNavController().navigate(R.id.action_reposFragment_to_errorFragment)
    }

    private fun goToEmptyView() {
        findNavController().navigate(R.id.action_reposFragment_to_emptyFragment)
    }

    private fun setRecyclerViewList(repos: List<Repos>) {
        binding.rvRepos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ReposAdapter(repos)
        }
    }

    private fun showLoadingState() {
        with(binding) {
            rvRepos.visibility = View.GONE
            containerLoadingState.visibility = View.VISIBLE
        }
    }

    private fun showSucessState() {
        with(binding) {
            rvRepos.visibility = View.VISIBLE
            containerLoadingState.visibility = View.GONE
        }
    }

}