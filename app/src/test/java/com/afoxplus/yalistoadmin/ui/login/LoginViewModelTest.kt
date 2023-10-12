package com.afoxplus.yalistoadmin.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.usecase.GetAuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.SaveAuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import com.afoxplus.yalistoadmin.ui.screens.login.LoginViewModel
import com.afoxplus.yalistoadmin.utils.TestCoroutineRule
import com.afoxplus.yalistoadmin.utils.UIKitCoroutineDispatcherTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private val mockUseCase: GetAuthUseCase = mock()

    private val mockSaveAuthUseCase: SaveAuthUseCase = mock()

    private val mockDispatcher: UIKitCoroutineDispatcher = UIKitCoroutineDispatcherTest()

    private lateinit var sutViewModel: LoginViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        sutViewModel = LoginViewModel(
            getAuthUseCase = mockUseCase,
            saveAuthUseCase = mockSaveAuthUseCase,
            dispatcher = mockDispatcher
        )
    }

    @Test
    fun `GIVEN ResultState is Success WHEN execute auth THEN return is Success`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val mockKey = ""
            val mockParams = AuthParams(mockKey)
            val mockReturn = ResultState.Success(
                Auth(
                    code = "Roberta",
                    urlImageLogo = "Mesha",
                    key = "Jesus",
                    name = "Khristopher"
                )
            )
            whenever(mockUseCase.auth(mockParams)).doReturn(mockReturn)

            // WHEN
            sutViewModel.auth(mockKey)

            // THEN
            verify(mockUseCase).auth(mockParams)
            assert(!sutViewModel.isLoading.value)
            assert(sutViewModel.isNavigate.value)
        }
    }

    @Test
    fun `GIVEN ResultState is Error WHEN execute auth THEN return is Error`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val mockKey = ""
            val mockParams = AuthParams(mockKey)
            val mockReturn = ResultState.Error<Auth>(Exception("Helouda"))
            whenever(mockUseCase.auth(mockParams)).doReturn(mockReturn)

            // WHEN
            sutViewModel.auth(mockKey)

            // THEN
            verify(mockUseCase).auth(mockParams)
            assert(!sutViewModel.isLoading.value)
        }
    }
}
