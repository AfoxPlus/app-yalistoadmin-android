package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import com.afoxplus.yalistoadmin.mock.data.listStates
import com.afoxplus.yalistoadmin.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GetStatesUseCaseTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mockRepository: StatesRepository = mock()

    private lateinit var sutUseCase: GetStatesUseCase

    @Before
    fun setup() {
        sutUseCase = GetStatesUseCase(repository = mockRepository)
    }

    @Test
    fun `GIVEN result states Success WHEN call getStates THEN return states list`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val response: ResultState<List<States>> = ResultState.Success(listStates)
            whenever(mockRepository.getStates()).thenReturn(
                response
            )
            // WHEN
            val result = sutUseCase.getStates()

            // THEN
            Assert.assertNotNull(result)
            Assert.assertEquals(response, result)
            Assert.assertTrue(result is ResultState.Success)
            verify(mockRepository).getStates()
        }
    }
}
