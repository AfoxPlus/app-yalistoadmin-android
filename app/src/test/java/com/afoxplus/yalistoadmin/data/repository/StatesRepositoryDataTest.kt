package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.StatesDbLocal
import com.afoxplus.yalistoadmin.data.datasource.StatesRemote
import com.afoxplus.yalistoadmin.domain.entities.States
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

@ExperimentalCoroutinesApi
class StatesRepositoryDataTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mockDataSourceRemote: StatesRemote = mock()

    private val mockDataSourceLocal: StatesDbLocal = mock()

    private lateinit var sutRepositoryData: StatesRepositoryData

    @Before
    fun setup() {
        sutRepositoryData = StatesRepositoryData(
            dataSourceRemote = mockDataSourceRemote,
            dataSourceLocal = mockDataSourceLocal
        )
    }

    @Test
    fun `GIVEN getStates local is empty WHEN call getState function THEN call getStates from remote`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val response: ResultState.Success<List<States>> = ResultState.Success(emptyList())
            whenever(mockDataSourceLocal.getStates()).thenReturn(response)
            whenever(mockDataSourceRemote.getStates()).thenReturn(response)

            // WHEN
            val result: ResultState<List<States>> = sutRepositoryData.getStates()

            // THEN
            Assert.assertNotNull(result)
            Assert.assertEquals(response, result)
            Assert.assertTrue(result is ResultState.Success)
            verify(mockDataSourceLocal).getStates()
            verify(mockDataSourceRemote).getStates()
        }
    }

    @Test
    fun `GIVEN getStates local is NOT empty WHEN call getState function THEN return data from local`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val response: ResultState.Success<List<States>> =
                ResultState.Success(listStates)
            whenever(mockDataSourceLocal.getStates()).thenReturn(response)

            // WHEN
            val result: ResultState<List<States>> = sutRepositoryData.getStates()

            // THEN
            Assert.assertNotNull(result)
            Assert.assertEquals(response, result)
            Assert.assertTrue(result is ResultState.Success)
            verify(mockDataSourceLocal).getStates()
        }
    }

    @Test
    fun `GIVEN getStates local returns Error WHEN call getState function THEN return Error`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val response: ResultState<List<States>> = ResultState.Error(Exception("Error"))
            whenever(mockDataSourceLocal.getStates()).thenReturn(response)

            // WHEN
            val result: ResultState<List<States>> = sutRepositoryData.getStates()

            // THEN
            Assert.assertNotNull(result)
            Assert.assertTrue(result is ResultState.Error)
            verify(mockDataSourceLocal).getStates()
        }
    }

    @Test
    fun `GIVEN saveStates local returns unit WHEN call saveStates function THEN return unit`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val response: ResultState<Unit> = ResultState.Success(Unit)
            val mockListStates: List<States> = listStates
            whenever(mockDataSourceLocal.saveStates(mockListStates)).thenReturn(response)

            // WHEN
            val result: ResultState<Unit> = sutRepositoryData.saveStates(mockListStates)

            // THEN
            Assert.assertNotNull(result)
            Assert.assertTrue(result is ResultState.Success)
            verify(mockDataSourceLocal).saveStates(mockListStates)
        }
    }
}
