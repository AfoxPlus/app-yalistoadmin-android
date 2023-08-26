package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
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
            val response: ResultState<List<StatesEntity>> = ResultState.Success(
                arrayListOf(
                    StatesEntity(
                        id = "64a4d7b39ee9ab69359e2c3b",
                        code = "DONE",
                        name = "Finalizado"
                    ),
                    StatesEntity(
                        id = "64a4d7b39ee9ab69359e2c3c",
                        code = "REJECTED",
                        name = "Rechazado"
                    ),
                    StatesEntity(
                        id = "64a4d7b39ee9ab69359e2c38",
                        code = "TODO",
                        name = "Pendiente"
                    ),
                    StatesEntity(
                        id = "64a4d7b39ee9ab69359e2c39",
                        code = "PROGRESS",
                        name = "Proceso"
                    ),
                    StatesEntity(
                        id = "64a4d7b39ee9ab69359e2c3a",
                        code = "DELIVERY",
                        name = "En camino"
                    )
                )
            )
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
