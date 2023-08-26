package com.afoxplus.yalistoadmin.data.datasource.local.db

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.local.db.dao.StatesDAO
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.toDB
import com.afoxplus.yalistoadmin.mock.data.listStatesEntity
import com.afoxplus.yalistoadmin.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class StatesDataSourceLocalTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mockDAO: StatesDAO = mock()

    private lateinit var sutDataSource: StatesDataSourceLocal

    @Before
    fun setup() {
        sutDataSource = StatesDataSourceLocal(statesDAO = mockDAO)
    }

    @Test
    fun `GIVEN a list of StatesEntity WHEN call saveStates THEN return ResultState Success`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            val response = Unit
            doNothing().whenever(mockDAO).saveStates(listStatesEntity.map { it.toDB() })

            // WHEN
            val result = sutDataSource.saveStates(listStatesEntity)

            // THEN
            Assert.assertNotNull(result)
            Assert.assertEquals(response, response)
            Assert.assertTrue(result is ResultState.Success)
            verify(mockDAO).saveStates(listStatesEntity.map { it.toDB() })
        }
    }

    @Test
    fun `GIVEN an exception WHEN call saveStates THEN return ResultState Error`() {
        testCoroutineRule.runBlockingTest {
            // GIVEN
            whenever(mockDAO.saveStates(listStatesEntity.map { it.toDB() })).thenAnswer {
                throw Exception("")
            }

            // WHEN
            val result = sutDataSource.saveStates(listStatesEntity)

            // THEN
            Assert.assertNotNull(result)
            Assert.assertTrue(result is ResultState.Error)
            verify(mockDAO).saveStates(listStatesEntity.map { it.toDB() })
        }
    }
}
