package com.example.githubrepos.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubrepos.MainDispatcherRule
import com.example.githubrepos.domain.model.User
import com.example.githubrepos.domain.repository.UsersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @MockK
    lateinit var repository: UsersRepository

    @MockK
    lateinit var error: Throwable

    private val viewModel by lazy {
        spyk(
            HomeViewModel(
                repository = repository
            )
        )
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

    }

    @Test
    fun `GIVEN user needs to getMovies WHEN call getMovies THEN return success `() = runTest {

        coEvery {
            repository.getUsers()
        } returns flow { listOf<User>() }

        viewModel.dispatcherViewAction(HomeViewAction.GetUsers)

        assertEquals(HomeViewState.Loading, viewModel.viewState.value)

    }

    @Test
    fun `GIVEN user needs to getMovies WHEN call getMovies THEN return error `() = runTest {


        coEvery {
            repository.getUsers()
        } returns flow { HomeViewState.UsersLoadFailure(error) }

        val observerMock: Observer<HomeViewState> = mockk()
        viewModel.viewState.observeForever(observerMock)

        viewModel.dispatcherViewAction(HomeViewAction.GetUsers)


    }
}