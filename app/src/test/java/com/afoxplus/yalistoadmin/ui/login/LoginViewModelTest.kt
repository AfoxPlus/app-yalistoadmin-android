package com.afoxplus.yalistoadmin.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.usecase.GetAuthUseCase
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
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

    private val mockDispatcher: UIKitCoroutineDispatcher = UIKitCoroutineDispatcherTest()

    private lateinit var sutViewModel: LoginViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setup() {
        sutViewModel = LoginViewModel(mockUseCase, mockDispatcher)
    }

    @Test
    fun `GIVEN ResultState is Success WHEN execute auth THEN return is Success`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val mockKey = ""
            val mockParams = AuthParams(mockKey)
            val mockReturn = ResultState.Success(
                AuthEntity(
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
            assert(sutViewModel.authEntity == (mockReturn.data as AuthEntity))
            assert(sutViewModel.authEntity.code == (mockReturn.data as AuthEntity).code)
        }
    }

    @Test
    fun `GIVEN ResultState is Error WHEN execute auth THEN return is Error`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val mockKey = ""
            val mockParams = AuthParams(mockKey)
            val mockReturn = ResultState.Error<AuthEntity>(Exception("Helouda"))
            whenever(mockUseCase.auth(mockParams)).doReturn(mockReturn)

            // WHEN
            sutViewModel.auth(mockKey)

            // THEN
            verify(mockUseCase).auth(mockParams)
            assert(!sutViewModel.isLoading.value)
        }
    }
}
